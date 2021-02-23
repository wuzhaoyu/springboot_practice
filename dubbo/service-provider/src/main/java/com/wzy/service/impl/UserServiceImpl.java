package com.wzy.service.impl;


import com.alibaba.dubbo.config.ServiceConfig;
import com.wzy.bean.UserVO;
import com.wzy.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.util.Date;

/**
 * @author Tommy
 * Created by Tommy on 2018/11/18
 **/
//@Service(async = true)
@Service
@Component
public class UserServiceImpl implements UserService {

    int port;

    @Override
    public UserVO getUser(Integer id) {
        UserVO u = new UserVO();
        u.setBirthDay(new Date());
        u.setId(id);
        u.setPort(port);
        // 获取当前用户名
        u.setName(ManagementFactory.getRuntimeMXBean().getName());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return u;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
