package com.zw.erp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zw.erp.mapper.OrdersMapper;
import com.zw.erp.pojo.Orders;
import com.zw.erp.service.OrdersService;
import com.zw.erp.pojo.Orderdetail;

@Service
public class OrdersServiceImpl implements OrdersService{
	@Autowired
	public OrdersMapper ordersMapper;

	public List<Orders> getList(String type) {
		// TODO Auto-generated method stub
		return ordersMapper.getList(type);
	}

	public void add(Orders orders) {
		// TODO Auto-generated method stub
		//1. 设置订单的状态
		orders.setState(Orders.STATE_CREATE);
		//3. 下单时间
		orders.setCreatetime(new Date());
		
		// 合计金额
		double total = 0;
		
		for(Orderdetail detail : orders.getOrderDetails()){
			//累计金额
			total += detail.getMoney();
			//明细的状态
			detail.setState(Orderdetail.STATE_NOT_IN);
			//跟订单的关系
			detail.setOrders_uuid(orders.getUuid());
		}
		//设置订单总金额
		orders.setTotalmoney(total);
		
		//保存到DB 
		ordersMapper.add(orders);
	}
	
}
