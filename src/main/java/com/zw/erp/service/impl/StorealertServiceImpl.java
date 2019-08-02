package com.zw.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zw.erp.mapper.StoredetailMapper;
import com.zw.erp.pojo.Storealert;
import com.zw.erp.service.StorealertService;

@Service
public class StorealertServiceImpl implements StorealertService{

	@Autowired
	private StoredetailMapper storedetailMapper;
	public List<Storealert> getStorealertList() {
		// TODO Auto-generated method stub
		return storedetailMapper.getStorealertList();
	}

}
