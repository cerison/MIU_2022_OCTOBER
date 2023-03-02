package lesson4.labsolns.prob1;

import java.time.LocalDate;

public class Order {
	private String orderNo;
	private LocalDate orderDate;
	private double orderAmount;
	public Order(LocalDate orderDate, double orderAmount) {
		this.orderDate = orderDate;
		this.orderAmount = orderAmount;
	}
	public void setOrderNo(String s) {
		orderNo = s;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	
}
