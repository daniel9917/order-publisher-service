package com.microservices.demo.order.publisher.service.kafka;

import com.microservices.demo.order.publisher.service.model.Order;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderStatusProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderStatusProducer.class);

    @Autowired
    private NewTopic topic;

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public OrderStatusProducer() {
    }

//    public OrderStatusProducer() {
//    }

    public void sendMessage (Order orderEvent) {
        LOGGER.info(String.format("Order event => %s", orderEvent.toString()));

//        // create Message
//        Message<Order> message = MessageBuilder
//                .withPayload(orderEvent)
//                .setHeader(KafkaHeaders.TOPIC, topic.name())
//                .build();

        ListenableFuture<SendResult<String, Order>> future =  kafkaTemplate.send(topic.name(), orderEvent);


        future.completable().whenComplete(((stringOrderSendResult, throwable) -> {
            if (throwable == null) {
                LOGGER.info("Successfully sent message with offset: " + stringOrderSendResult.getRecordMetadata().offset());
            } else {
                LOGGER.error("Unable to send message due to: " + throwable.getMessage());
            }
        }));

    }
}
