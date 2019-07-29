package com.zw.erp.mapper;

import java.util.List;
import com.zw.erp.pojo.Store;

public interface StoreMapper {
	public List<Store> findByCondition(Store store);
	public void insert(Store store);
	public void delete(long uuid);
	public void update(Store store);
	public Store get(long uuid);
}
