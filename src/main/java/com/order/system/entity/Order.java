package com.order.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore private Long id;
	
	@Column(name="ORDER_REFERENCE", nullable = false, unique = true)
	private String orderReference;
	
	@Column(name="NO_OF_BRICKS")
	private Long numberOfBricks;
	
	@Column(name="STATUS")
	@JsonIgnore private String status;
	
	@Column(name="CREATED_DT")
	private Date createdDate;
	
	@Column(name="UPDATED_DT")
	@JsonIgnore private Date updatedDate;
	
	@Column(name="DISPATCHED_DT")
	@JsonIgnore private Date dispatchedDate;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	
	public Order() {
		
	}

	public Order(String orderReference, Long numberOfBricks, Date createdDate, String status, Customer customer) {
		super();
		this.orderReference = orderReference;
		this.numberOfBricks = numberOfBricks;
		this.createdDate = createdDate;
		this.status = status;
		this.customer = customer;
	}

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getDispatchedDate() {
		return dispatchedDate;
	}

	public void setDispatchedDate(Date dispatchedDate) {
		this.dispatchedDate = dispatchedDate;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderReference=" + orderReference + ", numberOfBricks=" + numberOfBricks
				+ ", customer=" + customer + "]";
	}


}
