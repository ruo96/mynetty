package com.wrh.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:42 2019/5/21 0021
 * @Modified By:
 */
public class TestEnum {
    public static void main(String[] args) {
        System.out.println(FootEnum.APPLE.name());

        FootEnum fn = FootEnum.valueOf(FootEnum.APPLE.name());
        System.out.println("fn name is : " + fn.name());

        String companyId = "10020";
        companyId = StringUtils.leftPad(companyId, 4, '0');
        System.out.println(companyId);
        companyId = StringUtils.substring(companyId, -4);
        System.out.println(companyId);

    }
}
