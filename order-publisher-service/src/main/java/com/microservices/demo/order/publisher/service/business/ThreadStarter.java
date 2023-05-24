package com.microservices.demo.order.publisher.service.business;

import com.microservices.demo.order.publisher.service.concurrency.DishCreatorThread;
import com.microservices.demo.order.publisher.service.concurrency.WaiterThread;
import com.microservices.demo.order.publisher.service.kafka.OrderStatusProducer;
import com.microservices.demo.order.publisher.service.model.Dish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.PriorityQueue;
import java.util.Queue;

public class ThreadStarter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadStarter.class);
    private volatile Queue<Dish> dishes;
    private DishCreatorThread dishCreatorThread;
    private WaiterThread waiterThread;
    private OrderStatusProducer orderStatusProducer;

    public ThreadStarter(OrderStatusProducer orderStatusProducer) {
        this.dishes = new PriorityQueue<>();
        this.orderStatusProducer = orderStatusProducer;
    }

    public void initThreads () {
        dishCreatorThread = new DishCreatorThread(this.dishes);
        waiterThread = new WaiterThread(this.dishes, this.orderStatusProducer);
        LOGGER.info("Starting threads");
        dishCreatorThread.start();
        waiterThread.start();
    }
}
