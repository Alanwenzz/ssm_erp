package com.zw.erp.service;

import java.util.List;

import com.zw.erp.pojo.Goodstype;

public interface GoodstypeService {
	public void add(Goodstype goodstype);
	public void delete(long uuid);
	public void update(Goodstype goodstype);
	public List<Goodstype> findByCondition(Goodstype goodstype);
	public Goodstype get(long uuid);
}
