package com.easy.archiecture.interceptors;

import com.easy.archiecture.entity.User;
import com.easy.archiecture.service.UserServiceImpl;
import com.easy.archiecture.support.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (request.getRequestURI().contains("login") || request.getRequestURI().contains("registry")) {
            return true;
        }
        if (null != token) {
            User login = JWTUtil.unsign(token, User.class);
//            String loginId = request.getParameter("password");
            //解密用户信息，根据用户名密码查用户。
            if (null != login) {
                User loginResult = userService.findById(login.getId());
                if (loginResult != null) {
                    return true;
                } else {
                    responseMessage(response, response.getWriter());
                    return false;
                }
            } else {
                responseMessage(response, response.getWriter());
                return false;
            }
        } else {
            responseMessage(response, response.getWriter());
            return false;
        }
    }


    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out) {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(403);
        out.print("验证未通过");
        out.flush();
        out.close();
    }

}
