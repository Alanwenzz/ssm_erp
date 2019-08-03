package com.zw.erp.service.impl;

import java.util.Date;
import java.util.List;
import java.math.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zw.erp.mapper.OrderdetailMapper;
import com.zw.erp.mapper.OrdersMapper;
import com.zw.erp.pojo.Orderdetail;
import com.zw.erp.pojo.Orders;
import com.zw.erp.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService{
	@Autowired
	public OrdersMapper ordersMapper;
	@Autowired
	public OrderdetailMapper orderdetailMapper;

	public List<Orders> getList(String state,String type,int page,int rows) {
		// TODO Auto-generated method stub
		return ordersMapper.getList(state,type,page,rows);
	}

	public void add(Orders orders) {
		// TODO Auto-generated method stub
		//1. 设置订单的状态
		orders.setState(Orders.STATE_CREATE);
		//3. 下单时间
		orders.setCreatetime(new Date());
		
		// 合计金额
		double total = 0;
		BigDecimal t = new BigDecimal(Double.toString(total));
		
		//累计金额
		for(Orderdetail detail : orders.getOrderDetails()){
			BigDecimal s = new BigDecimal(Double.toString(detail.getMoney()));
			t=t.add(s);
		}
		
		//设置订单总金额
		orders.setTotalmoney(t.doubleValue());
		
		//订单保存到db
		ordersMapper.insert(orders);

		//插入订单条目
		for(Orderdetail detail : orders.getOrderDetails()){
			//明细的状态
			detail.setState(Orderdetail.STATE_NOT_IN);
			//跟订单的关系
			detail.setOrders_uuid(orders.getUuid());
			//订单条目保存到db
			orderdetailMapper.insert(detail);
		}
	}

	public long getCount(String state,String type) {
		// TODO Auto-generated method stub
		return ordersMapper.getCount(state,type);
	}

	public void doCheck(long oid, long uid,String name) {
		// TODO Auto-generated method stub
		Orders orders=ordersMapper.get(oid);
		
		//订单的状态
		if(!Orders.STATE_CREATE.equals(orders.getState())){
			try {
				throw new Exception("亲！该订单已经审核过了");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//1. 修改订单的状态
		orders.setState(Orders.STATE_CHECK);
		//2. 审核的时间
		orders.setChecktime(new Date());
		//3. 审核人
		orders.setChecker(uid);
		orders.setCheckerName(name);
		
		ordersMapper.update(orders);
	}

	public void doStart(long oid, long uid, String name) {
		// TODO Auto-generated method stub
		Orders orders=ordersMapper.get(oid);
		
		//订单的状态
		if(!Orders.STATE_CHECK.equals(orders.getState())){
			try {
				throw new Exception("亲！该订单已经确认过了");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//1. 修改订单的状态
		orders.setState(Orders.STATE_START);
		//2. 审核的时间
		orders.setStarttime(new Date());
		//3. 审核人
		orders.setStarter(uid);
		orders.setStarterName(name);
		
		ordersMapper.update(orders);
	}

}
