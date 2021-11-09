package com.easy.archiecture.dao;

import com.easy.archiecture.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
//@CacheConfig(cacheNames = "user")
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;



    public User findByUserId(int id) {
        final User user = new User();
        String sql = "SELECT id,name,age FROM t_user WHERE id=?";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setAge(resultSet.getInt(3));
            }
        });
//        List list = jdbcTemplate.queryForObject(sql, new Object[]{id}, List.class);
        return user;
    }


    public User findByName(String name) {


        final User user = new User();
        String sql = "SELECT id,name FROM t_user WHERE name=?";
        jdbcTemplate.query(sql, new Object[]{name}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
            }
        });
        return user;
    }

    public User findByNameAndPassword(String name, String password) {

        final User user = new User();
        String sql = "SELECT id,name FROM t_user WHERE name=? AND password=?";
        jdbcTemplate.query(sql, new Object[]{name, password}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
            }
        });
        return user;
    }

    public int insertUser(User user) {
        String sql = "INSERT INTO t_user (name,age,password) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getPassword());
    }


    public int updateUser(User user) {
        String sql = "UPDATE t_user SET name=? WHERE id=?";
        return jdbcTemplate.update(sql, user.getName(), user.getId());
    }
}
