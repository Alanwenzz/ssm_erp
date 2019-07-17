package com.zw.erp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zw.erp.mapper.MenuMapper;
import com.zw.erp.pojo.Menu;
import com.zw.erp.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuMapper menuMapper;
	public Menu get(String string) {
		// TODO Auto-generated method stub
		return menuMapper.get(string);
	}

}
