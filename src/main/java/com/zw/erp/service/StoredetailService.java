package com.zw.erp.service;
import java.util.List;
import com.zw.erp.pojo.Storedetail;

public interface StoredetailService {
	public void add(Storedetail storedetail);
	public void delete(long uuid);
	public void update(Storedetail storedetail);
	public List<Storedetail> findByCondition(Storedetail storedetail);
	public Storedetail get(long uuid);
}
