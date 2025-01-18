package com.just.order_generator.model;

import com.just.order_generator.constant.OrderType;

public class Order {
    private final OrderType type;
    private final double price;
    private final double quantity;
    private final double stopLoss;
    private final double takeProfit;

    public Order(OrderType type, double price, double quantity, double stopLoss, double takeProfit){
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
    }


    public OrderType getType(){ return this.type; }
    public double getPrice(){ return this.price; }
    public double getQuantity(){ return this.quantity; }
    public double getStopLoss(){ return this.stopLoss; }
    public double getTakeProfit(){ return this.takeProfit; }
}
