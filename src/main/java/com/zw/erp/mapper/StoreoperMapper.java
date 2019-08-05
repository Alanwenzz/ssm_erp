package com.zw.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zw.erp.pojo.Storeoper;

public interface StoreoperMapper {
	public List<Storeoper> findByCondition(Storeoper storeoper);
	public void insert(Storeoper storeoper);
	public void delete(long uuid);
	public void update(Storeoper storeoper);
	public Storeoper get(long uuid);
	public long getCount(@Param("so")Storeoper storeoper,@Param("ot")Date opertime2);
	public List<Storeoper> getListByPage(@Param("so")Storeoper storeoper,@Param("ot")Date opertime2,@Param("page")int firstResult, @Param("rows")int rows);
}
