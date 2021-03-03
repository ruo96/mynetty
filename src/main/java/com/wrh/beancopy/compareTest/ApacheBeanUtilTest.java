package com.wrh.beancopy.compareTest;

import com.wrh.beancopy.compareTest.bean.User;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;

/**
 * @author wuruohong
 * @Classname ApacheBeanUtilTest
 * @Description TODO
 * @Date 2021/1/21 16:51
 */
public class ApacheBeanUtilTest extends BaseCopyTest {
    @Override
    void warnUp() {
        /*User source = prepareOne();
        User target = new User();
        try {
            target = new User();
            System.out.println(source);
            BeanUtils.copyProperties(target, source);
            System.out.println(target);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    void copyLogic(List<User> data) {
        for(User source : data) {
            try {
//                BeanUtils.copyProperties(new User(), source);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    String name() {
        return "Apache BeanUtils";
    }
}
