package com.example.orderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryCompany implements Serializable {

    private String name;

    private String avgDeliveryTime;

    private double cost;
}
