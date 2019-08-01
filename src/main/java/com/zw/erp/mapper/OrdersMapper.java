package com.zw.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zw.erp.pojo.Orders;

public interface OrdersMapper {
	public List<Orders> getList(@Param("state")String state,@Param("type")String type,@Param("page")int page,@Param("rows")int rows);
	public void insert(@Param("o")Orders orders);
	public void delete(long uuid);
	public void update(Orders orders);
	public Orders get(long uuid);
	public long getCount();
}
