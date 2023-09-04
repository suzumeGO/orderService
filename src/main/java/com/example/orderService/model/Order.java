package com.example.orderService.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Order implements Serializable {
    private UUID id;
    private Customer customer;
    private List<String> products;
    private Payment payment;
    private DeliveryCompany delivery;

    public Order() {
        id = UUID.randomUUID();
        customer = new Customer();
        products = new ArrayList<>();
        payment = new Payment();
        delivery = new DeliveryCompany();
    }

}