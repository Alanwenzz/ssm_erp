package com.zw.erp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zw.erp.mapper.OrderdetailMapper;
import com.zw.erp.mapper.OrdersMapper;
import com.zw.erp.pojo.Orders;
import com.zw.erp.service.OrdersService;
import com.zw.erp.pojo.Orderdetail;

@Service
public class OrdersServiceImpl implements OrdersService{
	@Autowired
	public OrdersMapper ordersMapper;
	@Autowired
	public OrderdetailMapper orderdetailMapper;

	public List<Orders> getList(String type,int page,int rows) {
		// TODO Auto-generated method stub
		return ordersMapper.getList(type,page,rows);
	}

	public void add(Orders orders) {
		// TODO Auto-generated method stub
		//1. ���ö�����״̬
		orders.setState(Orders.STATE_CREATE);
		//3. �µ�ʱ��
		orders.setCreatetime(new Date());
		
		// �ϼƽ��
		double total = 0;
		
		//�ۼƽ��
		for(Orderdetail detail : orders.getOrderDetails()){
			total += detail.getMoney();
		}
		
		//���ö����ܽ��
		orders.setTotalmoney(total);
		
		//�������浽db
		ordersMapper.insert(orders);

		//���붩����Ŀ
		for(Orderdetail detail : orders.getOrderDetails()){
			//��ϸ��״̬
			detail.setState(Orderdetail.STATE_NOT_IN);
			//�������Ĺ�ϵ
			detail.setOrders_uuid(orders.getUuid());
			//������Ŀ���浽db
			orderdetailMapper.insert(detail);
			System.out.println(1);
		}
	}
	
}
