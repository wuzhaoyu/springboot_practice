package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/23
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
@Data
@AllArgsConstructor
public class Permissions {

    private String id;
    private String permissionsName;
}
