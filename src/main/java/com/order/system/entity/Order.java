package com.order.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Orders")
//@JsonIgnoreProperties(value = { "id" })
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore private Long id;
	
	@Column(name="ORDER_REFERENCE", nullable = false, unique = true)
	private String orderReference;
	
	@Column(name="NO_OF_BRICKS")
	private Long numberOfBricks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public Long getNumberOfBricks() {
		return numberOfBricks;
	}

	public void setNumberOfBricks(Long numberOfBricks) {
		this.numberOfBricks = numberOfBricks;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderReference=" + orderReference + ", numberOfBricks=" + numberOfBricks + "]";
	}

}
