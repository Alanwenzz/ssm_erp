package com.zw.erp.pojo;


import java.util.List;


public class Role {	

	private Long uuid;//���
	private String name;//����
	
	private List<Menu> menus;//��ɫ�µĲ˵�Ȩ��
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
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
	
	public boolean equals(Object obj) {  
        if (obj instanceof Role) {      
        	Role u = (Role) obj;           
		    return this.uuid.equals(u.uuid);        
		}        
		return super.equals(obj);  
	}
}


