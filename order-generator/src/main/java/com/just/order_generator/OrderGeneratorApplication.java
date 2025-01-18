package com.just.order_generator;

import com.just.order_generator.constant.OrderType;
import com.just.order_generator.model.Order;
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


		printOrders();
	}

	public static OrderType getRandomOrderType(){
		OrderType[] orderTypes = OrderType.values();
		int randomIndex = new Random().nextInt(orderTypes.length);
		return orderTypes[randomIndex];
	}

	public static Order generateOrder(){
		Random rand = new Random();
		DecimalFormat decimalFormat = new DecimalFormat("#.00000");

		//price range 0 - 0.56432
		double minPrice = 0.11111;
		double maxPrice = 0.56432;
		double minQuantity = 0.01;
		double maxQuantity = 3.00;

		double executionPrice = rand.nextDouble(minPrice, maxPrice);
		double quantity = rand.nextDouble(minQuantity, maxQuantity);
		double takeProfit = rand.nextDouble(executionPrice, maxPrice);
		double stopLoss = rand.nextDouble(minPrice, executionPrice);
		OrderType orderType = getRandomOrderType();

		//formatValues
		executionPrice = formatPrice(executionPrice);
		quantity = formatPrice(quantity);
		takeProfit = formatPrice(takeProfit);
		stopLoss = formatPrice(stopLoss);

		return new Order(orderType,executionPrice,quantity,stopLoss,takeProfit);
	}

	@Scheduled(fixedRate = 100)
	public static void printOrders(){
		String RESET = "\u001B[0m";
		String RED = "\u001B[31m";
		String GREEN = "\u001B[32m";

		Order newOrder = generateOrder();
		String color = (newOrder.getType() == OrderType.BUY) ||
				(newOrder.getType() == OrderType.BUY_LIMIT) ||
				(newOrder.getType() == OrderType.BUY_STOP) ? GREEN : RED;

		System.out.print(color + "[ ");
		System.out.print("ORDER TYPE: " + newOrder.getType().name() + "| ");
		System.out.print("PRICE: " + newOrder.getPrice() + "| ");
		System.out.print("QUANTITY: " + newOrder.getQuantity() + "| ");
		System.out.print("TAKE PROFIT: " + newOrder.getTakeProfit() + "| ");
		System.out.print("STOP LOSS: " + newOrder.getStopLoss() + " ");
		System.out.print(" ]\n" + RESET);
	}

	public static double formatPrice(double price){
		return Math.round(price * 1e5) / 1e5;
	}
}
