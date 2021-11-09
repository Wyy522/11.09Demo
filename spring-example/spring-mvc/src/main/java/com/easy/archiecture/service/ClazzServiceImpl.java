package com.easy.archiecture.service;

import com.easy.archiecture.dao.ClazzDao;
import com.easy.archiecture.entity.Clazz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ClazzServiceImpl {


    @Autowired
    private ClazzDao clazzDao;


    public int insertClazz(Clazz clazz) {
        return clazzDao.insertClazz(clazz);
    }

    @Cacheable(cacheNames = "clazz", key = "#clazzId")
    public Clazz findClazzById(int clazzId) {
        return clazzDao.findByUserId(clazzId);
    }
}
