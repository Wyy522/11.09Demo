package com.easy.archiecture.controller;


import com.easy.archiecture.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RedisDemoController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

//    @RequestMapping(value = "setString", method = RequestMethod.POST)
//    @ResponseBody
//    public void setString(@RequestBody User u) {
//        redisTemplate.opsForValue().set("user_1", u);
//    }
//
//
//    @RequestMapping(value = "getUser", method = RequestMethod.GET)
//    @ResponseBody
//    public User setString(@RequestParam int userId) {
//        return (User) redisTemplate.opsForValue().get("user_1");
//    }


}
