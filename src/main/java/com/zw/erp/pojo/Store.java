package com.zw.erp.pojo;

/**
 * �ֿ�ʵ����
 * @author Administrator *
 */
public class Store {	

	private Long uuid;//���
	private String name;//����
	private Long empuuid;//Ա�����

	public Long getUuid() {		
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getEmpuuid() {		
		return empuuid;
	}
	public void setEmpuuid(Long empuuid) {
		this.empuuid = empuuid;
	}

}

