package com.zw.erp.mapper;

import java.util.List;
import com.zw.erp.pojo.Goods;

public interface GoodsMapper {
	public List<Goods> findByCondition(Goods goods);
	public void insert(Goods goods);
	public void delete(long uuid);
	public void update(Goods goods);
	public Goods get(long uuid);
	public List<Goods> getList();
}
