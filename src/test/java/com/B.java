package com;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/27
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 * @description 说明：
 **/
public abstract class B {

    public static void main(String[] args) {
        B b = new A();
        b.b();
    }
    public void b (){
        a();
    }
    protected void a (){
    }
}
