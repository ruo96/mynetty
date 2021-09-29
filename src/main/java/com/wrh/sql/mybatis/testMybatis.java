package com.wrh.sql.mybatis;

import com.wrh.sql.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

/**
 * @author wuruohong
 * @Classname testMybatis
 * @Description TODO
 * @Date 2021/7/9 11:53
 */
public class testMybatis {

    @Test
    public void Test12() {
        SqlSessionFactory factory = null;
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

//        SqlSessionManager.newInstance(factory).


    }
}
