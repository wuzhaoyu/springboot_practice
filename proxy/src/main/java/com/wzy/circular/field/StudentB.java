package com.wzy.circular.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/21
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
@Component
public class StudentB {
    @Autowired
    private StudentC studentC ;
}
