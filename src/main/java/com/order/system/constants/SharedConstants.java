package com.order.system.constants;

public interface SharedConstants {

	String API_VERSION = "v1";

	String ORDER_SYSTEM_ROOT_PATH = "/api/" + API_VERSION + "/order";
	
	String CREATE_ORDER_PATH = "createOrder";
	
	String GET_ORDER_PATH = "getOrder";
	
	String ORDER_REFERENCE = "orderReference";
	
	String GET_ALL_ORDERS_PATH = "getAllOrders";
	
	String UPDATE_ORDER_PATH = "updateOrder";
	
	String IS_DISPATCH_REQ = "isDispatchRequest";
	
	String DISPATCH_STATUS = "DISPATCHED";
	
	String NEW_ORDER_STATUS = "NEW";

}
