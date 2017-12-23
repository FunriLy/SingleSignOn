package com.qg.fangrui.sso.demo;

import lombok.Value;

/**
 * 消费者实体类
 * @author FunriLy
 * Created by FunriLy on 2017/12/23.
 * From small beginnings comes great things.
 */
@Value
public class Customer {

    private long id;
    private String city;
    private String name;
    private  String street;
    private  String zip;
    private  String country;
}
