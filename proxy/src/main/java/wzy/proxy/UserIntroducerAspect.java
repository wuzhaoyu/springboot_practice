package wzy.proxy;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/18
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
@Aspect
@Component
public class UserIntroducerAspect {

    // + 标识不是userService本身，而是所有的子类
    @DeclareParents(value="wzy.service.UserService+",defaultImpl=BaseVerifierImpl.class)
    public static Verifier verifier;

}
