package com.easy.archiecture.dao;


import com.easy.archiecture.entity.Clazz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ClazzDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Clazz findByUserId(int id) {
        final Clazz clazz = new Clazz();
        String sql = "SELECT id,clazz_name,clazz_desc FROM t_clazz WHERE id=?";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                clazz.setId(resultSet.getInt(1));
                clazz.setClazzName(resultSet.getString(2));
                clazz.setClazzDesc(resultSet.getString(3));
            }
        });
//        List list = jdbcTemplate.queryForObject(sql, new Object[]{id}, List.class);
        return clazz;
    }


    public int insertClazz(Clazz clazz) {
        String sql = "INSERT INTO t_clazz (clazz_name,clazz_desc) VALUES (?,?)";
        return jdbcTemplate.update(sql, clazz.getClazzName(), clazz.getClazzDesc());
    }
}
