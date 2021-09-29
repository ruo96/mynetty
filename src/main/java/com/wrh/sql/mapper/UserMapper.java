package com.wrh.sql.mapper;

import com.wrh.sql.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    
    /**
     * insert
     * @param record 
     * @return
     */
    int insert(User record);
    
    /**
     * select
     * @param id 
     * @return
     */
    User selectById(Integer id);
    
    /**
     * update
     * @param record 
     * @return
     */
    int updateById(User record);
    
    /**
     * 根据用户名和密码查询
     * @param user
     * @return
     */
    User findByNameAndPwd(User user);
    
    /**
     * 根据设备号模糊查询
     *
     * @param udid
     * @return
     */
    User findByUdidLikely(String udid);

    /**
     * 根据用户名称查询
     * @param name
     * @return
     */
    User findByName(String name);
}