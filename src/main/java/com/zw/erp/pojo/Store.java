package com.zw.erp.pojo;

/**
 * �ֿ�ʵ����
 * @author Administrator *
 */
public class Store {	

	private Long uuid;//���
	private String name;//����
	private long empuuid;//Ա��
	private Emp emp;

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

	public long getEmpuuid() {
		return empuuid;
	}

	public void setEmpuuid(long empuuid) {
		this.empuuid = empuuid;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

}

