package com.zw.erp.mapper;

import java.util.List;
import com.zw.erp.pojo.Orders;

public interface OrdersMapper {
	public List<Orders> getList(String type);
	public void insert(Orders orders);
	public void delete(long uuid);
	public void update(Orders orders);
	public Orders get(long uuid);
	public void add(Orders orders);
}
