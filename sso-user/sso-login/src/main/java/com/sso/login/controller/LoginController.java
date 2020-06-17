package com.sso.login.controller;

import com.sso.login.pojo.User;
import com.sso.login.utils.LoginCacheUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author huixing
 * @description
 * @date 2020/6/15
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static Set<User> dbUsers;
    static {
        dbUsers = new HashSet<>();
        dbUsers.add(new User(0, "zhangsan", "12345"));
        dbUsers.add(new User(1, "zhangsan1", "12345"));
        dbUsers.add(new User(2, "zhangsan2", "12345"));
    }
    @PostMapping
    public String doLogin(User user, HttpSession session, HttpServletResponse response){
        String target = (String)session.getAttribute("target");
        Optional<User> first = dbUsers.stream().filter(dbUser -> dbUser.getUsername().equals(user.getUsername()) &&
                dbUser.getPassword().equals(user.getPassword())).findFirst();
        if (first.isPresent()){
            // 保存用户
            String token = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("TOKEN", token);
            LoginCacheUtil.loginUser.put(token, first.get());
            cookie.setDomain("codeshop.com");
            response.addCookie(cookie);
        } else {
            session.setAttribute("msg", "用户名密码错误");
            return "login";
        }
        //重定向到target地址
        return "redirect:" + target;
    }

    @GetMapping("info")
    @ResponseBody
    public ResponseEntity<User> getUserInfo(String token){
        if (!StringUtils.isEmpty(token)){
            User user = LoginCacheUtil.loginUser.get(token);
            return ResponseEntity.ok(user);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
