package com.wrh.enums.design;

public class TestRoleEnum {
    public static void main(String[] args) {
        JudgeRole judgeRole = new JudgeRole();
        System.out.println("role: " + RoleEnum.ROLE_ROOT_ADMIN.name());
        String permission = judgeRole.judge(RoleEnum.ROLE_ROOT_ADMIN.name());
        System.out.println("permission: " + permission);
    }
}
