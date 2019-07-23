package com.zw.erp.service;
import java.util.List;
import com.zw.erp.pojo.Goods;

public interface GoodsService {
	public void add(Goods goods);
	public void delete(long uuid);
	public void update(Goods goods);
	public List<Goods> findByCondition(Goods goods);
	public Goods get(long uuid);
	public List<Goods> getList();
}
