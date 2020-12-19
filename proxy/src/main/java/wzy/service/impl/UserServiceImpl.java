package wzy.service.impl;

import org.springframework.stereotype.Service;
import wzy.proxy.User;
import wzy.service.UserService;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/19
 * 修改说明
 *
 * @author wzy
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
