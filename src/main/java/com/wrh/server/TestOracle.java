package com.wrh.server;

import com.wrh.utils.DBUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:22 2018/7/25 0025
 * @Modified By:
 */
@Slf4j
public class TestOracle {

//    private ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    @Test
    public void testConn(){
        Connection conn = null;
        long start;
        long end;
        try {
            int i =1;
            for(;i<1000000;i++){
                start = System.currentTimeMillis();
                conn = DBUtil.getConnection();
                end = System.currentTimeMillis();
                log.info("第{}次连接数据库,耗时{}ms",i,end - start);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            DBUtil.close(conn);
        }
    }
}
