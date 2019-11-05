package com.order.system.spring;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderAppConfiguration {

	@Bean
	public DozerBeanMapper getBeanMapper() {
		return new DozerBeanMapper();
	}
	
}
