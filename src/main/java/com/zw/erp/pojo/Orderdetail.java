package com.zw.erp.pojo;


/**
 * ������ϸʵ����
 * @author Administrator *
 */

public class Orderdetail {	
	
	/**δ��� */
	public static final String STATE_NOT_IN = "0";
	/**�����*/
	public static final String STATE_IN = "1";
	//δ����
	public static final String STATE_NOT_OUT = "0";
	/**�ѳ���*/
	public static final String STATE_OUT = "1";

	private Long uuid;//���
	private Long goodsuuid;//��Ʒ���
	private String goodsname;//��Ʒ����
	private Double price;//�۸�
	private Long num;//����
	private Double money;//���
	private java.util.Date endtime;//��������
	private Long ender;//���Ա
	private Long storeuuid;//�ֿ���
	private String state;//�ɹ���0=δ��⣬1=�����  ���ۣ�0=δ���⣬1=�ѳ���
	/** ������ѭ�� */

	private Orders orders;//����

	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public Long getUuid() {		
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Long getGoodsuuid() {		
		return goodsuuid;
	}
	public void setGoodsuuid(Long goodsuuid) {
		this.goodsuuid = goodsuuid;
	}
	public String getGoodsname() {		
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public Double getPrice() {		
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getNum() {		
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public Double getMoney() {		
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public java.util.Date getEndtime() {		
		return endtime;
	}
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
	public Long getEnder() {		
		return ender;
	}
	public void setEnder(Long ender) {
		this.ender = ender;
	}
	public Long getStoreuuid() {		
		return storeuuid;
	}
	public void setStoreuuid(Long storeuuid) {
		this.storeuuid = storeuuid;
	}
	public String getState() {		
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
