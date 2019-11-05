package com.order.system.bto;

public class OrderBTO {
	
	private String orderReference;
	
	private Long numberOfBricks;

	private Long customerId;


	public OrderBTO() {
		
	}
	
	public OrderBTO(String orderReference, Long numberOfBricks, Long customerId) {
		super();
		this.orderReference = orderReference;
		this.numberOfBricks = numberOfBricks;
		this.customerId = customerId;
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "OrderBTO [orderReference=" + orderReference + ", numberOfBricks=" + numberOfBricks + ", customerId="
				+ customerId + "]";
	}

}

