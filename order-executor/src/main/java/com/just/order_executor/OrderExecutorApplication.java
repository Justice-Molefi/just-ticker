package com.just.order_executor;

import com.just.order_executor.model.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.lang.NonNull;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

@SpringBootApplication
public class OrderExecutorApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrderExecutorApplication.class, args);

		String url = "ws://localhost:8090/just-orders";

		WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		stompClient.connectAsync(url, new StompSessionHandlerAdapter(){

			@Override
			public void afterConnected(@NonNull StompSession session,@NonNull StompHeaders connectedHeader){
				System.out.println(":::Connected to Order Generator Server:::");
				session.subscribe("/topic/orders", new StompFrameHandler() {
					@Override
					@NonNull
					public Type getPayloadType(@NonNull StompHeaders headers) {
						return Order.class;
					}

					@Override
					public void handleFrame(@NonNull StompHeaders headers,@NonNull Object payload) {
						System.out.println("Received order: " + payload);
					}

				});
			}
		});
	}

}
