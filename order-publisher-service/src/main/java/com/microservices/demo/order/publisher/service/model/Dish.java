package com.microservices.demo.order.publisher.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Dish implements Comparable<Dish>{
    private String name;
    private int quantity;
    private List<Product> productList;

    @Override
    public int compareTo(Dish o) {
        return 0;
    }
}
