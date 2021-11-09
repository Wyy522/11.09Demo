package com.easy.archiecture.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.easy.archiecture.config.info.JdbcInfo;
import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan("com.easy.archiecture.dao.mapper")
@EnableTransactionManagement
public class DataConfig {

    @Resource
    private JdbcInfo jdbcInfo;

    @Bean
    public DataSource getDataSource() throws SQLException {
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setJdbcUrl(jdbcInfo.getUrl());
//        hikariDataSource.setUsername(jdbcInfo.getUsername());
//        hikariDataSource.setPassword(jdbcInfo.getPassword());
//        hikariDataSource.setDriverClassName(jdbcInfo.getDriver());


        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbcInfo.getUrl());
        dataSource.setUsername(jdbcInfo.getUsername());
        dataSource.setPassword(jdbcInfo.getPassword());
        dataSource.setDriverClassName(jdbcInfo.getDriver());
        dataSource.setFilters(jdbcInfo.getFilters());
        dataSource.setConnectionProperties(jdbcInfo.getConnectionProperties());
        return dataSource;
    }


    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    /**
     * 装配事务管理器
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * JDBC事务操作配置
     */
    @Bean(name = "txTemplate")
    public TransactionTemplate transactionTemplate(DataSourceTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }


    /**
     * mybatis
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setCache(new RedisCache(new PerpetualCache("default")));
        return sqlSessionFactoryBean;
    }


}
