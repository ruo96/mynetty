package com.wrh.controller.mapper;

import org.springframework.stereotype.Repository;

/**
 * @author
 * @Classname
 * @Description
 * @Date 2020/6/1
 */
@Repository
public interface TestMapper {

    /**
     * 查询统计信息
     * @return
     */
    Integer findCount();

}
