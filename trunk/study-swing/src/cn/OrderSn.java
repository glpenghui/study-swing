package cn;

public class OrderSn {

	private int orderId;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public String getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}
	public String getOrderMess() {
		return orderMess;
	}
	public void setOrderMess(String orderMess) {
		this.orderMess = orderMess;
	}
	private String orderSn,orderInfo,orderMess;
}
