package com.zw.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zw.erp.mapper.GoodsMapper;
import com.zw.erp.pojo.Goods;
import com.zw.erp.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsMapper goodsMapper;

	public void add(Goods goods) {
		// TODO Auto-generated method stub
		goodsMapper.insert(goods);
	}

	public void delete(long uuid) {
		// TODO Auto-generated method stub
		goodsMapper.delete(uuid);
	}

	public void update(Goods goods) {
		// TODO Auto-generated method stub
		goodsMapper.update(goods);;
	}

	public List<Goods> findByCondition(Goods goods) {
		// TODO Auto-generated method stub
		return goodsMapper.findByCondition(goods);
	}

	public Goods get(long uuid) {
		// TODO Auto-generated method stub
		return goodsMapper.get(uuid);
	}
	
}
