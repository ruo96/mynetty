package com.wrh.enums.design;

public class JudgeRole {
    public String judge(String rolename) {
        return RoleEnum.valueOf(rolename).op();
    }
}
