package com.sso.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author huixing
 * @description
 * @date 2020/6/16
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
   RestTemplate restTemplate;

    private final String LOGIN_INFO_ADDRESS = "http://login.codeshop.com:9000/login/info?token=";

    @GetMapping("/index")
    public String toIndex(@CookieValue(required = false, value = "TOKEN") Cookie cookie, HttpSession session)
    {
        if(cookie != null){
            String token = cookie.getValue();
            if (!StringUtils.isEmpty(token)){
                Map result = restTemplate.getForObject("http://login.codeshop.com:9000/login/info?token=" + token, Map.class);
                session.setAttribute("loginUser", result);
            }
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logout(@CookieValue(required = false, value = "TOKEN") Cookie cookie, HttpServletResponse response){
        if(cookie != null){
            cookie.setMaxAge(0);
            cookie.setPath("/");
        }
        return "index";
    }
}
