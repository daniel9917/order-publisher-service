package com.microservices.demo.order.publisher.service.model.builder;

import com.microservices.demo.order.publisher.service.model.Product;
import com.microservices.demo.order.publisher.service.model.ProductType;

public class ProductBuilder {

    public static Product chickpeaProtein () {
        return Product.builder()
                .name("Chickpea and Chia seeds Protein")
                .productType(ProductType.PROTEIN)
                .build();
    }

    public static Product beanProtein () {
        return Product.builder()
                .name("Bean and Chia seeds Protein")
                .productType(ProductType.PROTEIN)
                .build();
    }

    public static Product azukisProtein () {
        return Product.builder()
                .name("Azukis and Quinoa seeds Protein")
                .productType(ProductType.PROTEIN)
                .build();
    }

    /**
     *
     */

    public static Product pepper () {
        return Product.builder()
                .name("Pepper")
                .productType(ProductType.VEGETABLE)
                .build();
    }

    public static Product lettuce () {
        return Product.builder()
                .name("Lettuce")
                .productType(ProductType.VEGETABLE)
                .build();
    }

    public static Product onion () {
        return Product.builder()
                .name("onion")
                .productType(ProductType.VEGETABLE)
                .build();
    }

    public static Product tomato () {
        return Product.builder()
                .name("Tomato")
                .productType(ProductType.VEGETABLE)
                .build();
    }

    public static Product pickles () {
        return Product.builder()
                .name("Pickles")
                .productType(ProductType.VEGETABLE)
                .build();
    }

    /**
     *
     */

    public static Product burgerBun () {
        return Product.builder()
                .name("Burger Bun")
                .productType(ProductType.WRAP)
                .build();
    }

    public static Product multigrainBun () {
        return Product.builder()
                .name("Multigrain Bun")
                .productType(ProductType.WRAP)
                .build();
    }

    public static Product lettuceWrap () {
        return Product.builder()
                .name("Lettuce Wrap")
                .productType(ProductType.WRAP)
                .build();
    }

    public static Product mushroomsBread () {
        return Product.builder()
                .name("Portobello mushrooms bread")
                .productType(ProductType.WRAP)
                .build();
    }

    public static Product potatoBun () {
        return Product.builder()
                .name("Sweet Potato Buns")
                .productType(ProductType.WRAP)
                .build();
    }

    /**
     *
     */

    public static Product veganMayo () {
        return Product.builder()
                .name("Vegan Mayo")
                .productType(ProductType.SAUCE)
                .build();
    }

    public static Product bbq () {
        return Product.builder()
                .name("BBQ")
                .productType(ProductType.SAUCE)
                .build();
    }

    public static Product ketchup () {
        return Product.builder()
                .name("Ketchup")
                .productType(ProductType.SAUCE)
                .build();
    }

    public static Product hotSauce () {
        return Product.builder()
                .name("Hot Sauce")
                .productType(ProductType.SAUCE)
                .build();
    }

    public static Product mustard () {
        return Product.builder()
                .name("Mustard")
                .productType(ProductType.SAUCE)
                .build();
    }

    public static Product veganCheese () {
        return Product.builder()
                .name("Vegan Cheese")
                .productType(ProductType.SAUCE)
                .build();
    }

    public static Product pesto () {
        return Product.builder()
                .name("Pesto")
                .productType(ProductType.SAUCE)
                .build();
    }

    public static Product guacamole () {
        return Product.builder()
                .name("Guacamole")
                .productType(ProductType.SAUCE)
                .build();
    }

    public static Product hummus () {
        return Product.builder()
                .name("Hummus")
                .productType(ProductType.SAUCE)
                .build();
    }

    public static Product onionJam () {
        return Product.builder()
                .name("onionJam")
                .productType(ProductType.SAUCE)
                .build();
    }

//    public static Product  () {
//        return Product.builder()
//                .name()
//                .productType()
//                .build();
//    }
}
