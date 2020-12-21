package com.wzy.proxy;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/18
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 * @description 说明：
 **/
@Service
public class BaseVerifierImpl implements Verifier {
    public boolean validate(User user) {
        if (user.getUserName().equals("jack") && user.getPassword().equals("1234")) {
            System.out.println("校验成功");
            return true;
        }
        System.out.println("校验失败");
        return false;
    }
}
