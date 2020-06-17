package com.sso.login.utils;

import com.sso.login.pojo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huixing
 * @description
 * @date 2020/6/16
 */
public class LoginCacheUtil {
    public static Map<String, User> loginUser = new HashMap<>();
}
