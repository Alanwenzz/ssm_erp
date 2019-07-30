package com.zw.erp.service;

import java.util.List;

import com.zw.erp.pojo.Orders;

public interface OrdersService {

	List<Orders> getList(String type,int page,int rows);

	void add(Orders orders);

}
