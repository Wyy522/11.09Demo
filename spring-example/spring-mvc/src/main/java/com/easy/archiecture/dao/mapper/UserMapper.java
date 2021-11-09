package com.easy.archiecture.dao.mapper;

import com.easy.archiecture.entity.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Select(value = "SELECT id,name,age FROM t_user WHERE id=#{id}")
//    @Results(value = {
//            @Result(column = "id", property = "id"),
//            @Result(column = "name", property = "uName"),
//            @Result(column = "age", property = "uAge"),
//
//    })
    User findByUserId(@Param("id") int id);


    @Select(value = "SELECT id,name,age FROM t_user WHERE name=#{name}")
    User findByName(@Param("name") String name);


    @Select(value = "SELECT id,name FROM t_user WHERE name=#{name} AND password=#{password}")
    User findByNameAndPassword(@Param(value = "name") String name, @Param(value = "password") String password);

    @Insert(value = "INSERT INTO t_user (name,age,password) VALUES (#{user.name},#{user.age},#{user.password})")
    int insertUser(@Param(value = "user") User user);

    @Update(value = "UPDATE t_user SET name=#{user.name} WHERE id=#{user.id}")
    int updateUser(@Param(value = "user") User user);
}
