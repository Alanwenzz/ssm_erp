package com.zw.erp.service;

import java.util.List;

import com.zw.erp.pojo.Orders;

public interface OrdersService {

	List<Orders> getList(String state,String type,int page,int rows);

	void add(Orders orders);

	long getCount();

	void doCheck(long oid, long uid,String name);

	void doStart(long oid, long uid, String name);
}
