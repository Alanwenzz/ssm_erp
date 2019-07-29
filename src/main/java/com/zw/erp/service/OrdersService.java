package com.zw.erp.service;

import java.util.List;

import com.zw.erp.pojo.Orders;

public interface OrdersService {

	List<Orders> getList(String type);

	void add(Orders orders);

}
