package com.order.system.controller;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.order.system.bto.OrderBTO;
import com.order.system.bto.OrderResponse;
import com.order.system.constants.SharedConstants;
import com.order.system.entity.Customer;
import com.order.system.entity.Order;
import com.order.system.repository.OrderRepository;


@RestController
@RequestMapping(SharedConstants.ORDER_SYSTEM_ROOT_PATH)
public class OrderSystemController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderSystemController.class);
	
	@Autowired
	OrderRepository orderRepository;
	
	@PostMapping(path = SharedConstants.CREATE_ORDER_PATH, produces = "application/json")
	public ResponseEntity<Object> createOrder(@RequestBody OrderBTO orderBTO) {
		logger.info("OrderSystemController::createOrder::START OrderRequest = {}", orderBTO);
		if(orderBTO.getNumberOfBricks() == null || orderBTO.getCustomerId() == null) {
			return new ResponseEntity<Object>("Number of Bricks or Customer Id & can't null", new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		long recordsCount = orderRepository.count();
		Customer customer = new Customer();
		customer.setCustomerId(orderBTO.getCustomerId());
		Order order = new Order("ORD_"+recordsCount++, orderBTO.getNumberOfBricks(), new Date(), SharedConstants.NEW_ORDER_STATUS, customer);
		System.out.println("Order mappers : "+order);
		Order orderedData = orderRepository.save(order);
		return new ResponseEntity<>(new OrderResponse(orderedData.getOrderReference()), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping(path = SharedConstants.GET_ORDER_PATH, produces = "application/json")
	public ResponseEntity<Object> getOrder(@RequestParam(SharedConstants.ORDER_REFERENCE) final String orderReference) {
		logger.info("OrderSystemController::getOrder::START OrderReference = {}", orderReference);
		Optional<Order> optionalOrder = orderRepository.findByOrderReference(orderReference);
		if(optionalOrder.isPresent()) 
			return new ResponseEntity<Object>(optionalOrder, new HttpHeaders(), HttpStatus.OK);
		else
			return new ResponseEntity<Object>("Order Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path = SharedConstants.GET_ALL_ORDERS_PATH, produces = "application/json")
	public ResponseEntity<Object> getAllOrders() {
		logger.info("OrderSystemController::getAllOrders::START");
		return new ResponseEntity<Object>(orderRepository.findAll(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping(path = SharedConstants.UPDATE_ORDER_PATH, produces = "application/json")
	public ResponseEntity<Object> updateOrder(@RequestBody OrderBTO orderBTO, @RequestParam(SharedConstants.IS_DISPATCH_REQ)  boolean isDispatch) {
		logger.info("OrderSystemController::updateOrder::START OrderRequest = {}", orderBTO);
		Order order = null;
		Order orderedData = null;
		if (orderBTO.getNumberOfBricks() == null || orderBTO.getOrderReference() == null) {
			return new ResponseEntity<Object>("Number of Bricks or Order Reference can't be null", new HttpHeaders(),HttpStatus.BAD_REQUEST);
		} else if (orderBTO.getOrderReference().isEmpty()) {
			return new ResponseEntity<Object>("Order Reference can't empty", new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		Optional<Order> optionalOrder = orderRepository.findByOrderReference(orderBTO.getOrderReference().trim());
		if (optionalOrder.isPresent()) {
			order = optionalOrder.get();
			if(order.getStatus().equals(SharedConstants.DISPATCH_STATUS)) {
				return new ResponseEntity<>("Order Already Dispatched. Update Operation can't be performed", new HttpHeaders(), HttpStatus.BAD_REQUEST);
			} else {
				if(!isDispatch) {
					order.setNumberOfBricks(orderBTO.getNumberOfBricks());
				}else {
					order.setStatus(SharedConstants.DISPATCH_STATUS);
					order.setDispatchedDate(new Date());
				}
				order.setUpdatedDate(new Date());
				orderedData = orderRepository.save(order);
				return new ResponseEntity<>(new OrderResponse(orderedData.getOrderReference()), new HttpHeaders(), HttpStatus.OK);
			}
		} else
			return new ResponseEntity<>("Invalid Order Reference Received", new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
}
