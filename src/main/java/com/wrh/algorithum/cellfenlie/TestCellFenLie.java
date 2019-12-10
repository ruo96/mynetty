package com.wrh.algorithum.cellfenlie;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:10 2019/12/4 0004
 * @Modified By:
 */
@Slf4j
public class TestCellFenLie {
    public int allCells(int n) {
        return aCell(n) + bCell(n) + cCell(n);
    }

    /**
     * 第 n 小时 a 状态的细胞数
     */
    public int aCell(int n) {
        if(n==1){
            return 1;
        }else{
            return aCell(n-1)+bCell(n-1)+cCell(n-1);
        }
    }

    /**
     * 第 n 小时 b 状态的细胞数
     */
    public int bCell(int n) {
        if(n==1){
            return 0;
        }else{
            return aCell(n-1);
        }
    }

    /**
     * 第 n 小时 c 状态的细胞数
     */
    public int cCell(int n) {
        if(n==1 || n==2){
            return 0;
        }else{
            return bCell(n-1);
        }
    }

    @Test
    public void test1(){

        int count = allCells(3);
        log.info("===>{}",count);
    }
}
