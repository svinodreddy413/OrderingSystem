package com.order.system.bto;

public class OrderResponse {
	
	private String orderReference;

    public OrderResponse(String reference) { 
       this.orderReference = reference;
    }

	public String getOrderReference() {
		return orderReference;
	}

}
