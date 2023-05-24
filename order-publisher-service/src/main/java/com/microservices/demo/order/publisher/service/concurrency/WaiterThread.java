package com.microservices.demo.order.publisher.service.concurrency;

import com.microservices.demo.order.publisher.service.kafka.OrderStatusProducer;
import com.microservices.demo.order.publisher.service.model.Dish;
import com.microservices.demo.order.publisher.service.model.Order;
import com.microservices.demo.order.publisher.service.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

public class WaiterThread extends Thread implements OrderPublisherThread{

    private static final Logger LOGGER = LoggerFactory.getLogger(WaiterThread.class);

    private volatile Queue<Dish> dishQueue;

    private OrderStatusProducer orderStatusProducer;

    public WaiterThread(Queue<Dish> dishQueue, OrderStatusProducer orderStatusProducer) {
        this.dishQueue = dishQueue;
        this.orderStatusProducer = orderStatusProducer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!dishQueue.isEmpty()) {
                    publishOrder();
                    LOGGER.info("Published order at the head of the queue.");
                    Thread.sleep(500);
                }else {
                    LOGGER.info("Order queue is empty waiting 500ms to check again.");
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                LOGGER.error("Exception thrown in thread " +
                        Thread.currentThread().getName() +
                        ". Message: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public synchronized void publishOrder() {
        Order newOrder = Order.builder()
                .status(Status.PROCESSING)
                .dish(dishQueue.poll())
                .build();
        orderStatusProducer.sendMessage(newOrder);
        LOGGER.info("Successfully pulled order from the queue, new queue size is: " + dishQueue.size());
    }

}
