package com.wrh.collection.set;

import lombok.Data;

import java.util.Objects;

/**
 * @author wuruohong
 * @Classname RuleInfo
 * @Description TODO
 * @Date 2021/12/30 16:16
 */
@Data
public class RuleInfo {
    private String name;
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleInfo ruleInfo = (RuleInfo) o;
        return Objects.equals(name, ruleInfo.name) &&
                Objects.equals(age, ruleInfo.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
