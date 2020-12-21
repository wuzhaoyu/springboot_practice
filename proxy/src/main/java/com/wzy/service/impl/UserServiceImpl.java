package com.wzy.service.impl;

import org.springframework.stereotype.Service;
import com.wzy.proxy.User;
import com.wzy.service.UserService;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/19
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 * @description 说明：
 **/
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void save(User user) {
        System.out.println(user.toString() + "用户创建");
    }
}
