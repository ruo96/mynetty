package com.wrh.enums.design;

public enum RoleEnum implements RoleOperation {
    ROLE_ROOT_ADMIN{
        @Override
        public String op() {
            return "ROLE_ROOT_ADMIN:" + "has AAA permission";
        }
    },
    ROLE_ORDER_ADMIN{
        @Override
        public String op() {
            return "ROLE_ORDER_ADMIN:" + "has BBB permission";
        }
    },
    ROLE_NORMAL{
        @Override
        public String op() {
            return "ROLE_NORMAL:" + "has CCC permission";
        }
    };


}
