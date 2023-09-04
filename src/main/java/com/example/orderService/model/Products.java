package com.example.orderService.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Products implements Serializable {

    private List<String> selectedProducts = new ArrayList<>();

    public List<String> getSelectedProducts() {
        return selectedProducts;
    }
}
