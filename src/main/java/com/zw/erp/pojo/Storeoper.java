package com.zw.erp.pojo;

/**
 * �ֿ������¼ʵ����
 * @author Administrator *
 */
public class Storeoper {	
	/**1�����*/
	public static final String TYPE_IN = "1";
	/** 2������*/
	public static final String TYPE_OUT = "2";
	private Long uuid;//���
	private Long empuuid;//����Ա�����
	private String empName;
	private java.util.Date opertime;//��������
	private Long storeuuid;//�ֿ���
	private String storeName;
	private Long goodsuuid;//��Ʒ���
	private String goodsName;
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	private Long num;//����
	private String type;//1����� 2������

	public Long getUuid() {		
		return uuid;
	}
	
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Long getEmpuuid() {		
		return empuuid;
	}
	public void setEmpuuid(Long empuuid) {
		this.empuuid = empuuid;
	}
	public java.util.Date getOpertime() {		
		return opertime;
	}
	public void setOpertime(java.util.Date opertime) {
		this.opertime = opertime;
	}
	public Long getStoreuuid() {		
		return storeuuid;
	}
	public void setStoreuuid(Long storeuuid) {
		this.storeuuid = storeuuid;
	}
	public Long getGoodsuuid() {		
		return goodsuuid;
	}
	public void setGoodsuuid(Long goodsuuid) {
		this.goodsuuid = goodsuuid;
	}
	public Long getNum() {		
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public String getType() {		
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
