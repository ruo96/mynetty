package com.wrh.algorithum;

import akka.remote.artery.aeron.TaskRunner;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuruohong
 * @Classname TestStairs
 * @Description TODO
 * @Date 2020/12/26 10:27
 */
public class TestStairs {
    private Map<Integer, Integer> map = new HashMap<>();
    int result;
    public int climbStairs(int n) {
        if(n ==1){
            map.put(1,1);
            System.out.println("contain 1: "+map);
            return 1;
        }
        if(n ==2){
            map.put(2,2);
            System.out.println("contain 2: "+map);
            return 2;
        }
        if(map.containsKey(n)){
            System.out.println("contain: " +n +"  map: "+map);
            return map.get(n);
        }else{
            result = climbStairs(n-2) + climbStairs(n-1);
            map.put(n, result);
            System.out.println("not contain: " +n +"  map: "+map);
        }
        return result;
    }

    @Test
    public void Test37() {
        climbStairs(5);

    }


}
