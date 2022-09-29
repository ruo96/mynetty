package com.wrh.db.h2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/9/8 17:26
 */
@Entity
public class Product {
    @Id
    private String Id;

    private String name;
}
