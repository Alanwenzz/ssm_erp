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
		//1. ���ö�����״̬
		orders.setState(Orders.STATE_CREATE);
		//3. �µ�ʱ��
		orders.setCreatetime(new Date());
		
		// �ϼƽ��
		double total = 0;
		BigDecimal t = new BigDecimal(Double.toString(total));
		
		//�ۼƽ��
		for(Orderdetail detail : orders.getOrderDetails()){
			BigDecimal s = new BigDecimal(Double.toString(detail.getMoney()));
			t=t.add(s);
		}
		
		//���ö����ܽ��
		orders.setTotalmoney(t.doubleValue());
		
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
		}
	}

	public long getCount(String state,String type) {
		// TODO Auto-generated method stub
		return ordersMapper.getCount(state,type);
	}

	public void doCheck(long oid, long uid,String name) {
		// TODO Auto-generated method stub
		Orders orders=ordersMapper.get(oid);
		
		//������״̬
		if(!Orders.STATE_CREATE.equals(orders.getState())){
			try {
				throw new Exception("�ף��ö����Ѿ���˹���");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//1. �޸Ķ�����״̬
		orders.setState(Orders.STATE_CHECK);
		//2. ��˵�ʱ��
		orders.setChecktime(new Date());
		//3. �����
		orders.setChecker(uid);
		orders.setCheckerName(name);
		
		ordersMapper.update(orders);
	}

	public void doStart(long oid, long uid, String name) {
		// TODO Auto-generated method stub
		Orders orders=ordersMapper.get(oid);
		
		//������״̬
		if(!Orders.STATE_CHECK.equals(orders.getState())){
			try {
				throw new Exception("�ף��ö����Ѿ�ȷ�Ϲ���");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//1. �޸Ķ�����״̬
		orders.setState(Orders.STATE_START);
		//2. ��˵�ʱ��
		orders.setStarttime(new Date());
		//3. �����
		orders.setStarter(uid);
		orders.setStarterName(name);
		
		ordersMapper.update(orders);
	}

}
