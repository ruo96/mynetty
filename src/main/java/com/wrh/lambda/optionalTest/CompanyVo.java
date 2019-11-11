package com.wrh.lambda.optionalTest;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:31 2019/10/23 0023
 * @Modified By:
 */
@Accessors(chain = true)
@Data
public class CompanyVo {
    private String name;

    private int money;

    private Date date;
}
