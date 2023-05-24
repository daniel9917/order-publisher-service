package com.microservices.demo.order.publisher.service.concurrency;

import com.microservices.demo.order.publisher.service.model.Dish;

public interface OrderPublisherThread {
    void publishOrder ();
}
