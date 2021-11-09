package com.easy.archiecture.controller;

import com.easy.archiecture.entity.UserClazzRef;
import com.easy.archiecture.entity.vo.UserClazzVO;
import com.easy.archiecture.service.UserClazzServiceImpl;
import com.easy.archiecture.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserClazzController {

    @Autowired
    private UserClazzServiceImpl userClazzService;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "user_join_clazz", method = RequestMethod.POST)
    @ResponseBody
    public String userJoinClazz(@RequestBody UserClazzVO userClazzVO) {
        int joinInClazz = userClazzService.userJoinInClazz(userClazzVO.getUser().getId(), userClazzVO.getClazzId());
        if (joinInClazz != 0) {
            return "hello";
        }
        return "注册失败";
    }

    @RequestMapping(value = "user_update_and_change_clazz", method = RequestMethod.POST)
    @ResponseBody
    public String updateUserAndClazz(@RequestBody UserClazzVO userClazzVO) {
        UserClazzRef userClazz = userClazzService.findByUserId(userClazzVO.getUser().getId());
        int joinInClazz = userService.updateUserAndChangeUserClazz(userClazzVO.getUser(), userClazzVO.getClazzId(), userClazz.getClazzId());
        if (joinInClazz != 0) {
            return "success";
        }
        return "注册失败";
    }


    @RequestMapping(value = "find_userId_by_clazzId", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> registry(@RequestParam int clazzId) {
        return userClazzService.findUserIdByClazzId(clazzId);

    }


}
