package com.order.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.order.system.bto.OrderResponse;
import com.order.system.controller.OrderSystemController;
import com.order.system.entity.Order;
import com.order.system.repository.OrderRepository;

@SpringBootTest
public class OrderSystemControllerTest {

	@InjectMocks
	private OrderSystemController controller;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Test
	public void createOrder() {
		Order mockOrder = Mockito.mock(Order.class);
		when(orderRepository.save(mockOrder)).thenReturn(mockOrder);
		ResponseEntity<OrderResponse> orderResponse = controller.createOrder(mockOrder);
		assertNotNull(orderResponse);
		assertEquals(orderResponse.getStatusCodeValue(), 200);
	}
	
	@Test
	public void getOrder() {
		Order mockOrder = Mockito.mock(Order.class);
		Optional<Order> optionalOrder = Optional.of((Order) mockOrder);
		when(orderRepository.findByOrderReference(Mockito.anyString())).thenReturn((optionalOrder));
		ResponseEntity<Object> orderResponse = controller.getOrder(Mockito.anyString());
		assertNotNull(orderResponse);
		assertEquals(orderResponse.getStatusCodeValue(), 200);
	}
	
	@Test
	public void getAllOrders() {
		List<Order> mockList = new ArrayList<>();
		Order mockOrder = Mockito.mock(Order.class);
		mockList.add(mockOrder);
		when(orderRepository.findAll()).thenReturn((mockList));
		ResponseEntity<Object> orderResponse = controller.getAllOrders();
		assertNotNull(orderResponse);
		assertEquals(orderResponse.getStatusCodeValue(), 200);
		
	}
	
}
