package com.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/9/17
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 * @description 说明：
 **/
@Controller
@RequestMapping("/request")
public class PostController {

    @RequestMapping("/requestBody")
    public void requestBodyTest(Object object){
        Object result = object;
        System.out.println(result.toString());

    }
}
