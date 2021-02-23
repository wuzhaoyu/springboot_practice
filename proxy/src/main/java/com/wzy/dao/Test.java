package com.wzy.dao;

import com.wzy.config.UserConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/18
 * 修改说明
 *
 * @author com.com.com.wzy
 * @version V1.0
 * @description 说明：
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserConfig.class)
public class Test {

    @Autowired
    private Dao dao;

    @org.junit.Test
    public void addTest() {
        dao.query();
        dao.save("11");

    }
}
