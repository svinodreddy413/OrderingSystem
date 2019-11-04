package com.order.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.system.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
	
	//Order findByOrderReference(String orderReference);
	
	Optional<Order> findByOrderReference(String orderReference);

}
