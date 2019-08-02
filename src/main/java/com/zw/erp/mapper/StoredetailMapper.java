package com.zw.erp.mapper;

import java.util.List;

import com.zw.erp.pojo.Storealert;
import com.zw.erp.pojo.Storedetail;

public interface StoredetailMapper {
	public List<Storedetail> findByCondition(Storedetail storedetail);
	public List<Storealert> getStorealertList();
	public void insert(Storedetail storedetail);
	public void delete(long uuid);
	public void update(Storedetail storedetail);
	public Storedetail get(long uuid);
}
