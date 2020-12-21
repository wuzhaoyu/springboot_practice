package com.service.impl;

import com.entity.Permissions;
import com.entity.Role;
import com.entity.User;
import com.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public User getUserByName(String userName) {
        return getMapByName(userName);
    }

    public User getMapByName(String userName) {

        Permissions permissions1 = new Permissions("1", "query");
        Permissions permissions2 = new Permissions("2", "add");
        Set<Permissions> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);

        // 用户 com.wzy 角色：admin 权限： query add
        Role role = new Role("1", "admin", permissionsSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User("1", "com.wzy", "123456", roleSet);
        Map<String, User> map = new HashMap<>();
        map.put(user.getUserName(), user);

        // 用户 qz 角色：user 权限： query
        Set<Permissions> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions1);
        Role role1 = new Role("2", "user", permissionsSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User("2", "qz", "123456", roleSet1);
        map.put(user1.getUserName(), user1);
        return map.get(userName);

    }
}
