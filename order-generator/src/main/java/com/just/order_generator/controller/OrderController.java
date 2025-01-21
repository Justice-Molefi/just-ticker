package com.just.order_generator.controller;

import com.just.order_generator.model.Order;
import com.just.order_generator.service.OrderService;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Component
public class OrderController {
    private final OrderService orderService;
    private final SimpMessagingTemplate messagingTemplate;

    public OrderController(OrderService orderService, SimpMessagingTemplate messagingTemplate) {
        this.orderService = orderService;
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 100)
    public void sendOrder(){
        Order order = orderService.generateOrder();
        messagingTemplate.convertAndSend("/topic/orders", order);
    }
}
