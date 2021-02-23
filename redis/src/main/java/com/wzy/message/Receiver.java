package com.wzy.message;

import com.wzy.dao.FilmMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/8
 * 修改说明
 *
 * @author com.com.com.wzy
 * @version V1.0
 * @description 说明：
 **/
@Component
@Slf4j
public class Receiver {

    @Autowired
    private FilmMapper filmMapper;



    /**
     * 接受者
     * @param message
     */
    public void receiveMessage(String message) {
        filmMapper.stockOperate(Long.valueOf(message));
    }


}
