package com.wrh.calculate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:58 2019/1/9 0009
 * @Modified By:
 */
@Slf4j
public class TestCalculate {
    public static void main(String[] args) {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            j = j++;
        }
        System.out.println(j);
    }

    @Test
    public void Test() {
        String a = "123";
        Long b = Long.valueOf(a);
//        log.info("b:{}",b);
        System.out.println(b);
    }
    
    @Test
    public void Test31() {
        Integer now = 25461550;
        Integer total = 36000000;
        Integer left = total - now;
        Integer f3 = 3509;
        Integer f4 = 7018;
        Integer f5 = 14036;

        Integer f3Base = 1000000;
        Integer f4Base = 2000000;
        Integer f5Base = 4000000;

        System.out.println("now = " + now);
        System.out.println("left = " + left);
        Integer threshold = 20000;


        for (int i = 0; i < 286; i++) {
            int result = f3Base + (i*f3);
            for (int j = 0; j < 286; j++) {
                result += f4Base + f4*j;
                if (result >= left) {
                    continue;
                }
                int curr = left - result;
                if(curr >0 && curr < threshold){
                    System.out.println("i: " + i + "    j: " + j+ "    curr: " + curr+ "    result: " + result);
                }
                for (int k = 0; k < 286; k++) {
                    result += f5Base + f5*k;
                    if (result >= left) {
                        continue;
                    }
                    curr = left - result;
                    if(curr >0 && curr < threshold){
                        System.out.println("i: " + i + "    j: " + j+ "    k: " + k+ "    curr: " + curr+ "    result: " + result);
                        System.out.println("i * f3 + f3Base = " + (i * f3 + f3Base));
                        System.out.println("j * f4 + f4Base = " + (j * f4 + f4Base));
                        System.out.println("k * f5 + f5Base = " + (k * f5 + f5Base));
                    }

                }
            }
        }
      
    }
}
