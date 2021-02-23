package com.wzy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/8
 * 修改说明
 *
 * @author com.com.com.wzy
 * @version V1.0
 * @description 说明：
 **/
@Data
@Accessors(chain = true)
public class Film implements Serializable{

    @TableId
    private String id;
    private String name;
    private String text;
    private Long stock;


}
