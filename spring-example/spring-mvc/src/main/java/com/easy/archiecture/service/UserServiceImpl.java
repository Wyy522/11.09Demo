package com.easy.archiecture.service;

import com.easy.archiecture.dao.UserClazzRefDao;
import com.easy.archiecture.dao.UserDao;
import com.easy.archiecture.entity.User;
import com.easy.archiecture.entity.UserClazzRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.DigestUtils;

@Service
//@CacheConfig(cacheNames = "user")
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserClazzRefDao userClazzRefDao;

    @Autowired
    private TransactionTemplate txTemplate;


    public User findUserByName(String username) {
        return userDao.findByName(username);
    }

    public User findUserByNameAndPassword(String username, String password) {
        return userDao.findByNameAndPassword(username, password);
    }

    public boolean register(User user) {
        //用户重名校验
        User userByName = findUserByName(user.getName());
        if (userByName != null && userByName.getName() != null && userByName.getName().equals(user.getName())) {
            return true;
        }
        //对用户密码进行MD5,目的是，数据库中的敏感数据，不要存储明文。
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return userDao.insertUser(user) != 0;
    }

    @Cacheable(cacheNames = "user", key = "#id")
    public User findById(int id) {
        return userDao.findByUserId(id);
    }

    //CachePut会无条件的将方法的返回值写入指定的key中
//    @CachePut(cacheNames = "user", key = "#user.id")
    //CacheEvict按指定的key,清除缓存
    @CacheEvict(cacheNames = "user", key = "#user.id")
    public int updateUserName(User user) {
        return userDao.updateUser(user);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "user", key = "#user.id"),
            @CacheEvict(cacheNames = "ClazzUserIds", key = "#oldClazzId")})
    public int updateUserAndChangeUserClazz(User user, int clazzId, int oldClazzId) {
        //TODO:这里我少写了一些东西，需要未来加上。
        int updateUser = 0;
        int updateUserClazzRef = 0;
        Object execute = txTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
            try {
                userDao.updateUser(user);
                userClazzRefDao.updateUserClazzRef(new UserClazzRef(user.getId(), clazzId));
////                    userClazzRefDao.insertUserClazzRef(new UserClazzRef(user.getId(), clazzId));
            } catch (Throwable t) {
                transactionStatus.setRollbackOnly();
            }
            return null;
        }
    });

        return updateUser + updateUserClazzRef;
    }

    @Transactional
    public int insertUserAndJoinClazz(User user, int clazzId) {
        boolean register = register(user);
        if (register) {
            User result = findUserByName(user.getName());
            if (result == null) {
                return 0;
            }
            return userClazzRefDao.insertUserClazzRef(new UserClazzRef(result.getId(), clazzId));
        }
        return 0;
    }
}
