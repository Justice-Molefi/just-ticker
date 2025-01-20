package com.just.order_generator.service;

import com.just.order_generator.constant.OrderType;
import com.just.order_generator.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;

@Service
public class OrderService {

    @Value("${min.price}")
    private double minPrice;
    @Value("${max.price}")
    private double maxPrice;
    @Value("${min.quantity}")
    private double minQuantity;
    @Value("${max.quantity}")
    private double maxQuantity;

    public Order generateOrder(){
        Random rand = new Random();
        //price range 0 - 0.56432
//        double minPrice = 0.11111;
//        double maxPrice = 0.56432;
//        double minQuantity = 0.01;
//        double maxQuantity = 3.00;

        System.out.println("MIN PRICE: " + minPrice + "MAX PRICE: " + maxPrice + "MAX PRICE > MIN PRICE: " + (maxPrice > minPrice));
        double executionPrice = formatPrice(rand.nextDouble(minPrice, maxPrice));
        double quantity = formatPrice(rand.nextDouble(minQuantity, maxQuantity));
        double takeProfit = formatPrice(rand.nextDouble(executionPrice, maxPrice));
        double stopLoss = formatPrice(rand.nextDouble(minPrice, executionPrice));
        OrderType orderType = getRandomOrderType();

        return new Order(orderType,executionPrice,quantity,stopLoss,takeProfit);
    }

    @Scheduled(fixedRate = 100)
    public void printOrders(){
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";

        Order newOrder = generateOrder();
        String color = (newOrder.getType() == OrderType.BUY) ||
                (newOrder.getType() == OrderType.BUY_LIMIT) ? GREEN : RED;

        System.out.print(color + "[ ");
        System.out.print("ORDER TYPE: " + newOrder.getType().name() + "| ");
        System.out.print("PRICE: " + newOrder.getPrice() + "| ");
        System.out.print("QUANTITY: " + newOrder.getQuantity() + "| ");
        System.out.print("TAKE PROFIT: " + newOrder.getTakeProfit() + "| ");
        System.out.print("STOP LOSS: " + newOrder.getStopLoss() + " ");
        System.out.print(" ]\n" + RESET);
    }

    private OrderType getRandomOrderType(){
        OrderType[] orderTypes = OrderType.values();
        int randomIndex = new Random().nextInt(orderTypes.length);
        return orderTypes[randomIndex];
    }
    private double formatPrice(double price){
        return Math.round(price * 1e5) / 1e5;
    }

}
