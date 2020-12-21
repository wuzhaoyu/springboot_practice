package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/23
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 * @description 说明：
 **/
@Data
@AllArgsConstructor
public class User {

    private String id;
    private String userName;
    private String password;
    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;
}
