package com.wzy.controller;

import com.wzy.dao.FilmMapper;
import com.wzy.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/8
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
@Controller
public class FilmController {

    @Autowired
    private StringRedisTemplate template;

    /**
     * 使用消息队列的方式 使用大量请求快速返回，对数据库的操作延后进行。
     * 同时使用请求的消息进入队列中，有序的进行
     * @return
     */
    @RequestMapping(value = "/stock")
    @ResponseBody
    public String inset(){
        template.convertAndSend("stock","1");
        return "success";
    }
}
