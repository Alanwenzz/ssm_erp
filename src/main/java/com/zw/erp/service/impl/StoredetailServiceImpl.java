package com.zw.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zw.erp.mapper.StoredetailMapper;
import com.zw.erp.pojo.Storedetail;
import com.zw.erp.service.StoredetailService;

@Service
public class StoredetailServiceImpl implements StoredetailService{
	@Autowired
	private StoredetailMapper storedetailMapper;

	public void add(Storedetail storedetail) {
		// TODO Auto-generated method stub
		storedetailMapper.insert(storedetail);
	}

	public void delete(long uuid) {
		// TODO Auto-generated method stub
		storedetailMapper.delete(uuid);
	}

	public void update(Storedetail storedetail) {
		// TODO Auto-generated method stub
		storedetailMapper.update(storedetail);;
	}

	public List<Storedetail> findByCondition(Storedetail storedetail) {
		// TODO Auto-generated method stub
		return storedetailMapper.findByCondition(storedetail);
	}

	public Storedetail get(long uuid) {
		// TODO Auto-generated method stub
		return storedetailMapper.get(uuid);
	}
	
}
