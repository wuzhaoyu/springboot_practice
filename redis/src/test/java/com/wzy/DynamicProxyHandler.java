package com.wzy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/15
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class DynamicProxyHandler implements InvocationHandler {

    private Object object;

    public DynamicProxyHandler(Object o){
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买房前1");
        Object invoke = method.invoke(object, args);
        System.out.println("买房后1");
        return invoke;
    }

    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseWork();
        BuyHouse buyHouse1 = (BuyHouse) Proxy.newProxyInstance(BuyHouse.class.getClassLoader(), new Class[]{BuyHouse.class}, new DynamicProxyHandler(buyHouse));
        buyHouse1.buyHouseMethod();

    }
}
