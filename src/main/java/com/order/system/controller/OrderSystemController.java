package com.order.system.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.system.bto.OrderResponse;
import com.order.system.constants.SharedConstants;
import com.order.system.entity.Order;
import com.order.system.repository.OrderRepository;

@RestController
@RequestMapping(SharedConstants.ORDER_SYSTEM_ROOT_PATH)
public class OrderSystemController {

	
	@Autowired
	OrderRepository orderRepository;
	
	@GetMapping
    public ResponseEntity<Object> get() {
        return new ResponseEntity<Object>("SUCCESS", new HttpHeaders(), HttpStatus.OK);
    }
	
	@PostMapping(path = SharedConstants.CREATE_ORDER_PATH, produces = "application/json")
	public ResponseEntity<OrderResponse> createOrder(@RequestBody Order order) {
		System.out.println("Order Request : --> "+order);
		long recordsCount = orderRepository.count();
		order.setOrderReference("ORD_"+recordsCount++);
		Order orderedData = orderRepository.save(order);
		return new ResponseEntity<>( new OrderResponse(orderedData.getOrderReference()), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping(path = SharedConstants.GET_ORDER_PATH, produces = "application/json")
	public ResponseEntity<Object> getOrder(@RequestParam(SharedConstants.ORDER_REFERENCE) final String orderReference) {
		System.out.println("Order Reference : --> "+orderReference);
		Optional<Order> optionalOrder = orderRepository.findByOrderReference(orderReference);
		if(optionalOrder.isPresent()) 
			return new ResponseEntity<Object>(optionalOrder, new HttpHeaders(), HttpStatus.OK);
		else
			return new ResponseEntity<Object>("Order Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path = SharedConstants.GET_ALL_ORDER_PATH, produces = "application/json")
	public ResponseEntity<Object> getAllOrders() {
		return new ResponseEntity<Object>(orderRepository.findAll(), new HttpHeaders(), HttpStatus.OK);
	}
	
}
