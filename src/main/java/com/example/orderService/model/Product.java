package com.example.orderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private String name;
    private String description;
    private double price;
    private String size;
    public enum Size {
        SMALL("Маленькая"), MEDIUM("Средняя"), LARGE("Большая");
        private final String name;
        Size(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
}
