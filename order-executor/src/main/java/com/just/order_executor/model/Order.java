package com.just.order_executor.model;

import com.just.order_executor.constant.OrderType;

public class Order {
    private OrderType type;
    private double price;
    private double quantity;
    private double stopLoss;
    private double takeProfit;

    public Order(){}
    public OrderType getType(){ return this.type; }
    public double getPrice(){ return this.price; }
    public double getQuantity(){ return this.quantity; }
    public double getStopLoss(){ return this.stopLoss; }
    public double getTakeProfit(){ return this.takeProfit; }
}
