package com.wrh.copy.vo;

import lombok.Data;

import java.util.Objects;

/**
 * 节点属性
 *
 * @author zing
 * @date 2018/4/27 17:33
 */
@Data
public class NodeProperty {


    /**
     * 属性名称
     */
    private String propertyName;
    /**
     * 默认值 S0003 为地理位置
     */
    private String defaultValue;
    /**
     * 值类型
     */
    private int dataType;
    /**
     * 属性ID
     */
    private int propertyId;
    /**
     *默认类型
     */
    private String defaultType;
    /**
     * 节点ID
     */
    private int stageId;

    /**
     * 排序
     */
    private int sortCode;

    /**
     * 允许追加，1 允许，0 不允许
     */
    private int allowAdd;

    /**
     * 是否允许显示
     */
    private int allowApp;

    private String pictureSkipUrl;

    /**
     * 新增加的链接
     */
    private String linkValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NodeProperty property = (NodeProperty) o;
        return dataType == property.dataType &&
                propertyId == property.propertyId &&
                stageId == property.stageId &&
                sortCode == property.sortCode &&
                allowAdd == property.allowAdd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataType, propertyId, stageId, sortCode, allowAdd);
    }
}
