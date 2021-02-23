package com.wzy.dao;

import org.springframework.stereotype.Component;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/20
 * 修改说明
 *
 * @author com.com.wzy
 * @version V1.0
 * @description 说明：
 **/
@Component
public class DaoImpl implements Dao {
    public void query() {
        System.out.println("query");
    }

    public void save(String arg) {
        System.out.println(arg + "save");
    }
}
