package com.wzy.controller;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.wzy.bean.UserVO;
import com.wzy.service.UserService;
import org.apache.dubbo.rpc.support.RpcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 类功能说明:
 * 类修改者	创建日期2021/2/9
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
@RestController
public class ProductController {

    @Reference
    private UserService userService;



    /**
     * 添加完 返回总共消费
     * @param a
     * @return
     */
    @RequestMapping("/add")
    public String getCost(int a){
        // 设置服务实现对象
        UserVO user = userService.getUser(a);
        Future<UserVO> future = RpcContext.getContext().getFuture();
        UserVO user1 = userService.getUser(a);
        Future<UserVO> future1 = RpcContext.getContext().getFuture();
        try {
            long start = System.currentTimeMillis();
            System.out.println(future.get());
            System.out.println(future1.get());
            System.out.println(System.currentTimeMillis() - start );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "获取 ："+ future1;
    }
}
