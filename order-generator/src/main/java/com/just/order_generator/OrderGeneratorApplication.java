package com.just.order_generator;

import com.just.order_generator.constant.OrderType;
import com.just.order_generator.model.Order;
import com.just.order_generator.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DecimalFormat;
import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class OrderGeneratorApplication {
    public static void main(String[] args) {
		SpringApplication.run(OrderGeneratorApplication.class, args);
		OrderService orderService = new OrderService();
		orderService.printOrders();
	}








}
