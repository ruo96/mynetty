package com.wrh.beancopy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:36 2019/8/31 0031
 * @Modified By:
 */
@Data
public class GroupInfo {

    private int groupId;

    private String name;

    private Integer integerValue;

    private String strValue;

    private Boolean boolValue;

    private Long longValue;
}
