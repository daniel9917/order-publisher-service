package com.microservices.demo.order.publisher.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Product {
    private String name;
    private UUID productId;
    private ProductType productType;
}
