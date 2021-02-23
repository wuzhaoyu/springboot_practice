package com.proxy;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/18
 * 修改说明
 *
 * @author com.com.com.wzy
 * @version V1.0
 * @description 说明：
 **/
public class BaseVerifierImpl implements Verifier {
    @Override
    public boolean validate(User user) {
        if (user.getUserName().equals("jack") && user.getPassword().equals("1234")) {
            return true;
        }
        return false;
    }
}
