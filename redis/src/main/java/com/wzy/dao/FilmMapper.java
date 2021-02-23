package com.wzy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzy.entity.Film;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/8
 * 修改说明
 *
 * @author com.com.com.wzy
 * @version V1.0
 * @description 说明：
 **/
@Repository
public interface FilmMapper extends BaseMapper<Film> {

    public void stockOperate(@Param("id") Long id);


}
