package com.easy.archiecture.dao;


import com.easy.archiecture.entity.Clazz;
import com.easy.archiecture.entity.User;
import com.easy.archiecture.entity.UserClazzRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserClazzRefDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Cacheable(cacheNames = "userClazzRef", key = "#id")
    public UserClazzRef findByUserId(int id) {
        final UserClazzRef userClazzRef = new UserClazzRef();
        String sql = "SELECT user_id,clazz_id FROM t_clazz_user_ref WHERE user_id=?";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                userClazzRef.setUserId(resultSet.getInt(1));
                userClazzRef.setClazzId(resultSet.getInt(2));
            }
        });
        return userClazzRef;
    }


    public List<Integer> findByClazzId(int clazzId) {
        String sql = "SELECT user_id FROM t_clazz_user_ref WHERE clazz_id=?";
        return jdbcTemplate.query(sql, new Object[]{clazzId}, new RowMapper<Integer>() {

            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        });
    }


    public int insertUserClazzRef(UserClazzRef userClazzRef) {
        String sql = "INSERT INTO t_clazz_user_ref (user_id,clazz_id) VALUES (?,?)";
        return jdbcTemplate.update(sql, userClazzRef.getUserId(), userClazzRef.getClazzId());
    }


    public int updateUserClazzRef(UserClazzRef userClazzRef) {
        throw new RuntimeException();
//        String sql = "UPDATE t_clazz_user_ref SET clazz_id=? WHERE user_id=?";
//        return jdbcTemplate.update(sql, userClazzRef.getClazzId(), userClazzRef.getUserId());
    }
}
