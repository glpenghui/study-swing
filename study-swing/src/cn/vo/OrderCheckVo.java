package cn.vo;

public class OrderCheckVo {
	private String orderSn;//�������
	private String shippingSn;//�˵���
	private String productSn;//������
	private Integer subId;//subId
	private Integer qc_num;//��������
	private Integer unqc_num;//δ��������
	private String pickNo;//����� pickNo
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public String getShippingSn() {
		return shippingSn;
	}
	public void setShippingSn(String shippingSn) {
		this.shippingSn = shippingSn;
	}
	public String getProductSn() {
		return productSn;
	}
	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public Integer getQc_num() {
		return qc_num;
	}
	public void setQc_num(Integer qc_num) {
		this.qc_num = qc_num;
	}
	public Integer getUnqc_num() {
		return unqc_num;
	}
	public void setUnqc_num(Integer unqc_num) {
		this.unqc_num = unqc_num;
	}
	public String getPickNo() {
		return pickNo;
	}
	public void setPickNo(String pickNo) {
		this.pickNo = pickNo;
	}
}
