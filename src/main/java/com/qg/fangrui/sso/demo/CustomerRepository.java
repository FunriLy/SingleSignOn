package com.qg.fangrui.sso.demo;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 消费者实体
 * @author FunriLy
 * Created by FunriLy on 2017/12/23.
 * From small beginnings comes great things.
 */
@Component
public class CustomerRepository {

    private static List<Customer> customers = Arrays.asList(
            new Customer(1,"guangzhou","Twitter Inc.","1355 Market Street","CA 94103","USA"),
            new Customer(2,"beijing","Facebook Inc.","1 Hacker Way","CA 94025","USA"),
            new Customer(3,"hangzhou","Google Inc.","1600 Amphitheatre Parkway","CA 94043","USA"),
            new Customer(4,"nanjing","Oracle Corp.","500 Oracle Parway","CA 94065","USA"),
            new Customer(5,"chongqing","Netflix Inc.","100 Winchester Circle","CA 95932","USA")
    );


    public List<Customer> findAll() {
        return customers;
    }

    public Customer findOne(int id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst().get();
    }
}
