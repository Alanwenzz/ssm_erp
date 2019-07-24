package com.zw.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zw.erp.mapper.GoodstypeMapper;
import com.zw.erp.pojo.Goodstype;
import com.zw.erp.service.GoodstypeService;

@Service
public class GoodstypeServiceImpl implements GoodstypeService{
	@Autowired
	private GoodstypeMapper goodstypeMapper;

	public void add(Goodstype goodstype) {
		// TODO Auto-generated method stub
		goodstypeMapper.insert(goodstype);
	}

	public void delete(long uuid) {
		// TODO Auto-generated method stub
		goodstypeMapper.delete(uuid);
	}

	public void update(Goodstype goodstype) {
		// TODO Auto-generated method stub
		goodstypeMapper.update(goodstype);
	}

	public List<Goodstype> findByCondition(Goodstype goodstype) {
		// TODO Auto-generated method stub
		return goodstypeMapper.findByCondition(goodstype);
	}

	public Goodstype get(long uuid) {
		// TODO Auto-generated method stub
		return goodstypeMapper.get(uuid);
	}

}
