package com.zw.erp.pojo;


/**
 * �ֿ���ʵ����
 * @author Administrator *
 */

public class Storedetail {	

	private Long uuid;//���
	private Long storeuuid;//�ֿ���
	private String storeName;//�ֿ�����
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	private Long goodsuuid;//��Ʒ���
	private String goodsName;//��Ʒ����
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	private Long num;//����

	public Long getUuid() {		
		return uuid;
	}
	
	public void setUuid(Long uuid) {
		this.uuid = uuid;
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

}
