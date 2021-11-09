package com.easy.archiecture.dao;

import com.easy.archiecture.entity.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int insertLog(LogInfo logInfo) {
        String sql = "INSERT INTO t_log_info (log_op,log_type,user_id,create_time,url) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql, logInfo.getLogOp(), logInfo.getLogType(), logInfo.getUserId(), logInfo.getCreateTime(), logInfo.getUrl());
    }
}
