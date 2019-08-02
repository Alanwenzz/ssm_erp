package com.zw.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zw.erp.pojo.Storeoper;

public interface StoreoperMapper {
	public List<Storeoper> findByCondition(Storeoper storeoper);
	public void insert(Storeoper storeoper);
	public void delete(long uuid);
	public void update(Storeoper storeoper);
	public Storeoper get(long uuid);
	public long getCount();
	public List<Storeoper> getListByPage(@Param("page")int firstResult, @Param("rows")int rows);
}
