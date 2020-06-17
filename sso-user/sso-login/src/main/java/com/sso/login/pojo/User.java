package com.sso.login.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author huixing
 * @description
 * @date 2020/6/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true) // 链式调用
public class User {
    private Integer id;
    private String username;
    private String password;
}
