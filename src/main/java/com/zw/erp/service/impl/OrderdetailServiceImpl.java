package com.zw.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zw.erp.mapper.OrderdetailMapper;
import com.zw.erp.pojo.Orderdetail;
import com.zw.erp.service.OrderdetailService;

@Service
public class OrderdetailServiceImpl implements OrderdetailService{
	@Autowired
	private OrderdetailMapper orderdetailMapper;

	public void add(Orderdetail orderdetail) {
		// TODO Auto-generated method stub
		orderdetailMapper.insert(orderdetail);
	}

	public void delete(long uuid) {
		// TODO Auto-generated method stub
		orderdetailMapper.delete(uuid);
	}

	public void update(Orderdetail orderdetail) {
		// TODO Auto-generated method stub
		orderdetailMapper.update(orderdetail);;
	}

	public List<Orderdetail> findByCondition(Orderdetail orderdetail) {
		// TODO Auto-generated method stub
		return orderdetailMapper.findByCondition(orderdetail);
	}

	public Orderdetail get(long uuid) {
		// TODO Auto-generated method stub
		return orderdetailMapper.get(uuid);
	}
	
}
