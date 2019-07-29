package com.zw.erp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zw.erp.mapper.EmpMapper;
import com.zw.erp.mapper.MenuMapper;
import com.zw.erp.pojo.Emp;
import com.zw.erp.pojo.Menu;
import com.zw.erp.pojo.Role;
import com.zw.erp.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService{
	@Autowired
	private EmpMapper empMapper;
	@Autowired
	private MenuMapper menuMapper;

	public void add(Emp emp) {
		// TODO Auto-generated method stub
		emp.setPassword("123456");
		empMapper.insert(emp);
	}

	public void delete(long uuid) {
		// TODO Auto-generated method stub
		empMapper.delete(uuid);
	}

	public void update(Emp emp) {
		// TODO Auto-generated method stub
		empMapper.update(emp);;
	}

	public List<Emp> findByCondition(Emp emp, Date birthday2) {
		// TODO Auto-generated method stub
		return empMapper.findByCondition(emp, birthday2);
	}
	
	public Emp findByUsernameAndPwd(Emp emp) {
		return empMapper.findByUsernameAndPwd(emp);
	}

	public Emp get(long uuid) {
		// TODO Auto-generated method stub
		return empMapper.get(uuid);
	}

	public void updatePwd(long uuid, String txtOldPass, String txtNewPass) {
		// TODO Auto-generated method stub
		Emp emp=empMapper.get(uuid);
		if(!emp.getPassword().equals(txtOldPass)) {
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		empMapper.updatePwd(uuid,txtNewPass);
	}

	public List<Menu> getMenuByEmpuuid(long id) {
		// TODO Auto-generated method stub
		return empMapper.getMenuByEmpuuid(id);
	}

	public Menu readMenusByEmpuuid(long id) {
		//获取所用的菜单
		Menu root=menuMapper.get("-1");
		//用户下菜单集合
		List<Menu> lMenus=empMapper.getMenuByEmpuuid(id);
		//根菜单
		Menu menu=cloneMenu(root);
		
		//循环匹配模板
		Menu _m1=null;
		Menu _m2=null;
		for(Menu m1 : root.getMenus()) {
			_m1=cloneMenu(m1);//m1下有无子菜单不知道
			//二级菜单循环
			for(Menu m2 :m1.getMenus()) {
				
				if(lMenus.contains(m2)){
					//复制菜单
					_m2 = cloneMenu(m2);
					//加入到上级菜单下
					_m1.getMenus().add(_m2);
				}

			}
			
			//有二级菜单加进去
			if(_m1.getMenus().size()>0) {
				menu.getMenus().add(_m1);
			}
		}
		
		return menu;
	} 
	
	
	/**
	 * 复制menu
	 * @param src
	 * @return
	 */
	private Menu cloneMenu(Menu src){
		Menu menu = new Menu();
		menu.setIcon(src.getIcon());
		menu.setMenuid(src.getMenuid());
		menu.setMenuname(src.getMenuname());
		menu.setUrl(src.getUrl());
		menu.setMenus(new ArrayList<Menu>());
		return menu;
	}

	public List<Role> readEmpRoles(long uuid) {
		// TODO Auto-generated method stub
		return empMapper.readEmpRoles(uuid);
	}

	public List<Emp> getAll() {
		// TODO Auto-generated method stub
		return empMapper.getAll();
	}

	public void updateEmpRoles(long uuid, String checkedStr) {
		// TODO Auto-generated method stub
		//删除当前用户所有角色
		empMapper.deleteAllErole(uuid);
		//把id分割出来
		String[] ids = checkedStr.split(",");
		//循环插入
		for(String id:ids) {
			empMapper.insertErole(uuid,Integer.parseInt(id));
		}
	}

}
