package com.wrh.spring.source;


import org.springframework.stereotype.Component;

/**
 * @author wuruohong
 * @Classname UserService
 * @Description TODO
 * @Date 2021/12/2 11:53
 */
@Component
//@Scope("prototype")
//@Lazy
public class UserService {

    private OrderService orderService;
    private OrderService orderService1;
    private OrderService orderService2;

    public UserService() {

    }

    public UserService(OrderService orderService) {
        this.orderService = orderService;
        System.out.println("1");
    }

    public UserService(OrderService orderService, OrderService orderService1) {
        this.orderService = orderService;
        this.orderService1 = orderService1;
        System.out.println("2");
    }

    public UserService(OrderService orderService, OrderService orderService1, OrderService orderService2) {
        this.orderService = orderService;
        this.orderService1 = orderService1;
        this.orderService2 = orderService2;
        System.out.println("3");
    }

    public void test() {
        System.out.println("userService test");
        System.out.println(orderService +"===="+ orderService1 +"====" + orderService2);
    }
}
