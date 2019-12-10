package com.wrh.basicUse.vo;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:04 2019/11/28 0028
 * @Modified By:
 */
public class StudentVo  {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentVo studentVo = (StudentVo) o;
        return age == studentVo.age &&
                Objects.equals(name, studentVo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }*/
}
