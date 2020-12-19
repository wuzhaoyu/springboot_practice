package wzy.proxy;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wzy.config.UserConfig;
import wzy.service.UserService;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/18
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserConfig.class)
public class Test {
    @Autowired
    private UserService userService;

    @org.junit.Test
    public void addTest() {
        User user = new User();
        user.setPassword("12345");
        user.setUserName("jack");
        Verifier v = (Verifier) userService;
        if (v.validate(user)) {
            userService.save(user);
        }

    }
}
