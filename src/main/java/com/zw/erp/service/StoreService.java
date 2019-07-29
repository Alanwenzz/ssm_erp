package com.zw.erp.service;
import java.util.List;
import com.zw.erp.pojo.Store;

public interface StoreService {
	public void add(Store store);
	public void delete(long uuid);
	public void update(Store store);
	public List<Store> findByCondition(Store store);
	public Store get(long uuid);
}
