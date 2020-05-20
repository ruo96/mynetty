/*

 */
package com.wrh.enums.role;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

/**
 * 类TourMarkEnum.java的实现描述：游客标识枚举
 */
@Getter
public enum TourMarkEnum {

    /** 游客 **/
    TOUR_MARK("1", "游客"),
    /** 用户 **/
    USER_MARK("0", "正式用户");

    private TourMarkEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /** 编码 **/
    private String code;
    /** 备注 **/
    private String desc;

    /**
     * 游客编码是否有效
     * 
     * @param code 游客编码
     * @return true: 有效游客编码; false: 无效游客编码
     */
    public static boolean isValid(String code) {
        code = StringUtils.trim(code);
        // 兼容历史 该字段为新增字段
        if (StringUtils.isEmpty(code)) {
            return true;
        }
        return TOUR_MARK.getCode().equals(code) || USER_MARK.getCode().equals(code);
    }

    /**
     * 是否为正式用户
     * 
     * @param code 游客编码
     * @return true 正式用户； false： 不是正式用户
     */
    public static boolean isUserMark(String code) {
        code = StringUtils.trim(code);
        
        return StringUtils.isEmpty(code) || USER_MARK.getCode().equals(code);
    }

    /**
     * 是否为游客
     * 
     * @param code 游客编码
     * @return true 游客; false： 不是游客
     */
    public static boolean isTourMark(String code) {
        code = StringUtils.trim(code);
        return TOUR_MARK.getCode().equals(code);
    }

    public static TourMarkEnum getByCode(String code) {
        for (TourMarkEnum t : TourMarkEnum.values()) {
            if (t.code.equalsIgnoreCase(code)) {
                return t;
            }
        }
        return null;
    }


}
