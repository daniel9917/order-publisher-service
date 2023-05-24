package com.microservices.demo.order.publisher.service.controller;


import com.microservices.demo.order.publisher.service.business.ThreadStarter;
import com.microservices.demo.order.publisher.service.kafka.OrderStatusProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private OrderStatusProducer orderStatusProducer;

    private ThreadStarter threadStarter;

    public boolean threadsStarted = false;

    public TestController(OrderStatusProducer orderStatusProducer) {
        this.orderStatusProducer = orderStatusProducer;
        this.threadStarter = new ThreadStarter(orderStatusProducer);
    }

    @GetMapping("/hello")
    public String hello () {
        return "hello";
    }

    @GetMapping("/start-threads")
    public ResponseEntity<Boolean> startThreads () {
        LOGGER.info("Request to start threads being handled.");
        return new ResponseEntity<>(
                !threadsStarted ? initThreads() : false,
                HttpStatus.OK);
    }

    private boolean initThreads() {
        if (!threadsStarted) {
            this.threadStarter.initThreads();
            threadsStarted = true;
        }
        return threadsStarted;
    }
}
