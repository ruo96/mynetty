package com.wrh.beancopy;

import lombok.Data;

import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:35 2019/8/31 0031
 * @Modified By:
 */
@Data
public class PdaUser {

    private List<GroupInfo> groupInfoList;

    private int id;

    private String userName;
}
