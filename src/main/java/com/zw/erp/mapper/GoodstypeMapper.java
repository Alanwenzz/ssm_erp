package com.zw.erp.mapper;

import java.util.List;
import com.zw.erp.pojo.Goodstype;

public interface GoodstypeMapper {
	public List<Goodstype> findByCondition(Goodstype goodstype );
	public void insert(Goodstype goodstype );
	public void delete(long uuid);
	public void update(Goodstype goodstype );
	public Goodstype get(long uuid);
}
