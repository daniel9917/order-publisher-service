package com.microservices.demo.order.publisher.service.concurrency;

import com.microservices.demo.order.publisher.service.model.Dish;
import com.microservices.demo.order.publisher.service.model.Product;
import com.microservices.demo.order.publisher.service.model.builder.DishBuilder;
import com.microservices.demo.order.publisher.service.model.builder.ProductBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Queue;

public class DishCreatorThread extends Thread{

    private static final Logger LOGGER = LoggerFactory.getLogger(DishCreatorThread.class);

    private volatile Queue <Dish> dishQueue;

    private final Random RANDOM  = new Random();


    public DishCreatorThread(Queue<Dish> dishQueue) {
        this.dishQueue = dishQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                dishQueue.add(randomDish());
                LOGGER.info("Adding random dish to the queue.");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOGGER.error("Exception thrown in thread " +
                        Thread.currentThread().getName() +
                        ". Message: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized Dish randomDish () {
        int random = RANDOM.nextInt(4);
        return Dish.builder()
                .name(getRandomDishName(random))
                .quantity(random != 0 ? random : 1 )
                .productList(getRandomProductList(random))
                .build();
    }

    public synchronized String getRandomDishName (int random) {
         switch (random) {
             case 0:
                 return DishBuilder.dishNameHamburger();
             case 1:
                 return DishBuilder.dishNameLettuceWrap();
             case 2:
                 return DishBuilder.dishNameMushroomWrap();
             case 3:
                 return DishBuilder.dishNameMultigrainWrap();
             default:
                 return "No name";
         }
    }

    public synchronized List <Product> getRandomProductList (int random) {
        ArrayList <Product> productList = new ArrayList<>();
        productList.addAll(getRandomSauces());
        productList.addAll(getRandomVegetables());
        productList.add(getRandomProtein());
        productList.add(getRandomWrap());
        return productList;
    }

    public synchronized Product getRandomProtein () {
        int random = RANDOM.nextInt(3);
        switch (random) {
            case 0:
                return ProductBuilder.azukisProtein();
            case 1:
                return ProductBuilder.chickpeaProtein();
            default:
                return ProductBuilder.beanProtein();
        }
    }

    public synchronized List<Product> getRandomVegetables () {
        int random = RANDOM.nextInt(5);
        ArrayList <Product> vegetables = new ArrayList<>();
        int count = 0;
        while (count++ < (random != 0 ? random : 1)) {
            int newRandom = RANDOM.nextInt(5);
            switch (newRandom) {
                case 0:
                    vegetables.add(ProductBuilder.onion());
                    break;
                case 1:
                    vegetables.add(ProductBuilder.pickles());
                    break;
                case 2:
                    vegetables.add(ProductBuilder.pepper());
                    break;
                case 3:
                    vegetables.add(ProductBuilder.lettuce());
                    break;
                default:
                    vegetables.add(ProductBuilder.tomato());
                    break;
            }
        }
        return vegetables;
    }

    public synchronized List <Product> getRandomSauces () {
        int random = RANDOM.nextInt(10);
        ArrayList <Product> sauces = new ArrayList<>();
        int count = 0;
        while (count++ < (random != 0 ? random : 1)) {
            int newRandom = RANDOM.nextInt(10);
            switch (newRandom) {
                case 0:
                    sauces.add(ProductBuilder.veganMayo());
                    break;
                case 1:
                    sauces.add(ProductBuilder.bbq());
                    break;
                case 2:
                    sauces.add(ProductBuilder.hotSauce());
                    break;
                case 3:
                    sauces.add(ProductBuilder.mustard());
                    break;
                case 4:
                    sauces.add(ProductBuilder.veganCheese());
                    break;
                case 5:
                    sauces.add(ProductBuilder.ketchup());
                    break;
                case 6:
                    sauces.add(ProductBuilder.pesto());
                    break;
                case 7:
                    sauces.add(ProductBuilder.guacamole());
                    break;
                case 8:
                    sauces.add(ProductBuilder.hummus());
                    break;
                default:
                    sauces.add(ProductBuilder.onionJam());
                    break;
            }
        }
        return sauces;
    }

    public synchronized Product getRandomWrap () {
        int random = RANDOM.nextInt(5);
        switch (random) {
            case 0:
                return ProductBuilder.burgerBun();
            case 1:
                return ProductBuilder.multigrainBun();
            case 2:
                return ProductBuilder.lettuceWrap();
            case 3:
                return ProductBuilder.mushroomsBread();
            default:
                return ProductBuilder.potatoBun();
        }
    }

}
