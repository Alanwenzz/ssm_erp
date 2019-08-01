package com.zw.erp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zw.erp.mapper.OrderdetailMapper;
import com.zw.erp.mapper.OrdersMapper;
import com.zw.erp.mapper.StoreMapper;
import com.zw.erp.mapper.StoredetailMapper;
import com.zw.erp.mapper.StoreoperMapper;
import com.zw.erp.pojo.Orderdetail;
import com.zw.erp.pojo.Store;
import com.zw.erp.service.OrderdetailService;
import com.zw.erp.pojo.Orders;
import com.zw.erp.pojo.Storeoper;
import com.zw.erp.pojo.Storedetail;

@Service
public class OrderdetailServiceImpl implements OrderdetailService{
	@Autowired
	private OrderdetailMapper orderdetailMapper;
	@Autowired
	private StoredetailMapper storedetailMapper;
	@Autowired
	private StoreMapper storeMapper;
	@Autowired
	private StoreoperMapper storeoperMapper;
	@Autowired
	private OrdersMapper ordersMapper;

	public void add(Orderdetail orderdetail) {
		// TODO Auto-generated method stub
		orderdetailMapper.insert(orderdetail);
	}

	public void delete(long uuid) {
		// TODO Auto-generated method stub
		orderdetailMapper.delete(uuid);
	}

	public void update(Orderdetail orderdetail) {
		// TODO Auto-generated method stub
		orderdetailMapper.update(orderdetail);;
	}

	public List<Orderdetail> findByCondition(Orderdetail orderdetail) {
		// TODO Auto-generated method stub
		return orderdetailMapper.findByCondition(orderdetail);
	}

	public Orderdetail get(long uuid) {
		// TODO Auto-generated method stub
		return orderdetailMapper.get(uuid);
	}

	//���
	public void doInStore(Long odid, Long storeuuid, long eid,String ename) {
		// TODO Auto-generated method stub
		//******** ��1�� ������Ʒ��ϸ**********
		//1. ��ȡ��ϸ��Ϣ
		Orderdetail detail = orderdetailMapper.get(odid);
		//2. �ж���ϸ��״̬��һ����δ����
		if(!Orderdetail.STATE_NOT_IN.equals(detail.getState())){
			try {
				throw new Exception("�����ظ����");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//3. �޸�״̬Ϊ�����
		detail.setState(Orderdetail.STATE_IN);
		//4. ���ʱ��
		detail.setEndtime(new Date());
		//5. ���Ա
		detail.setEnder(eid);
		//6. �뵽�ĸ��ֿ�
		detail.setStoreuuid(storeuuid);
		orderdetailMapper.update(detail);
		
		//*******��2 �� ������Ʒ�ֿ���*********
		//1. ������ѯ������
		Storedetail storedetail = new Storedetail();
		//��ȡ�ֿ�����
		Store store=storeMapper.get(storeuuid);
		storedetail.setStoreName(store.getName());
		
		storedetail.setGoodsName(detail.getGoodsname());
		storedetail.setGoodsuuid(detail.getGoodsuuid());
		storedetail.setStoreuuid(storeuuid);
		
		//2. ͨ����ѯ ����Ƿ���ڿ����Ϣ
		List<Storedetail> storeList = storedetailMapper.findByCondition(storedetail);
		if(storeList.size()>0){
			//���ڵĻ�����Ӧ���ۼ���������
			long num = 0;
			if(null != storeList.get(0).getNum()){
				num = storeList.get(0).getNum().longValue();
			}
			storedetail.setNum(num + detail.getNum());
			storedetail.setUuid(storeList.get(0).getUuid());
			storedetailMapper.update(storedetail);
		}else{
			//�����ڣ���Ӧ�ò�����ļ�¼
			storedetail.setNum(detail.getNum());
			storedetailMapper.insert(storedetail);
		}
		
		//****** ��3�� ��Ӳ�����¼*****
		//1. ����������¼
		Storeoper log = new Storeoper();
		log.setEmpName(ename);
		log.setGoodsName(detail.getGoodsname());
		log.setStoreName(store.getName());
		log.setEmpuuid(eid);
		log.setGoodsuuid(detail.getGoodsuuid());
		log.setNum(detail.getNum());
		log.setOpertime(detail.getEndtime());
		log.setStoreuuid(storeuuid);
		log.setType(Storeoper.TYPE_IN);
		storeoperMapper.insert(log);
		
		//**** ��4�� �Ƿ���Ҫ���¶�����״̬********
		
		//1. ������ѯ����
		Orderdetail queryParam = new Orderdetail();
		long oid=detail.getOrders_uuid();
		Orders orders=ordersMapper.get(oid);
		//2. ��ѯ�������Ƿ񻹴���״̬Ϊ0����ϸ
		queryParam.setState(Orderdetail.STATE_NOT_IN);
		queryParam.setOrders_uuid(oid);
		//3. ���� getCount�������������Ƿ����״̬Ϊ0����ϸ
		long count = orderdetailMapper.getCount(queryParam);
		if(count == 0){
			//4. ���е���ϸ���Ѿ������ˣ���Ҫ���¶�����״̬���رն���
			orders.setState(Orders.STATE_END);
			orders.setEndtime(detail.getEndtime());
			orders.setEnder(eid);
			orders.setEnderName(ename);
			ordersMapper.update(orders);
		}
	}

	public void doOutStore(Long odid, Long storeuuid, long eid, String ename) {
		// TODO Auto-generated method stub
		//1. ��ȡ��ϸ��Ϣ
		Orderdetail detail = orderdetailMapper.get(odid);
		//2. �ж���ϸ��״̬��һ����δ�����
		if(!Orderdetail.STATE_NOT_OUT.equals(detail.getState())){
			try {
				throw new Exception("�����ظ�����");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//3. �޸�״̬Ϊ�ѳ���
		detail.setState(Orderdetail.STATE_OUT);
		//4. ����ʱ��
		detail.setEndtime(new Date());
		//5. ���Ա
		detail.setEnder(eid);
		//6. ���ĸ��ֿ��
		detail.setStoreuuid(storeuuid);
		orderdetailMapper.update(detail);
		
		//*******��2 �� ������Ʒ�ֿ���*********
		//1. ������ѯ������
		Storedetail storedetail = new Storedetail();
		//��ȡ�ֿ�����
		Store store=storeMapper.get(storeuuid);
		storedetail.setStoreName(store.getName());
		
		storedetail.setGoodsName(detail.getGoodsname());
		storedetail.setGoodsuuid(detail.getGoodsuuid());
		storedetail.setStoreuuid(storeuuid);
		
		//2. ͨ����ѯ ����Ƿ��п��
		List<Storedetail> storeList = storedetailMapper.findByCondition(storedetail);
		if(storeList.size()>0){
			//�п��  �ж��Ƿ�
			Storedetail sd=storeList.get(0);
			sd.setNum(sd.getNum() - detail.getNum());
			if(sd.getNum() < 0){
				try {
					throw new Exception("��治��");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			storedetail.setNum(sd.getNum());
			storedetail.setUuid(storeList.get(0).getUuid());
			storedetailMapper.update(storedetail);
		}else{
			try {
				throw new Exception("��治��");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//****** ��3�� ��Ӳ�����¼*****
		//1. ����������¼
		Storeoper log = new Storeoper();
		log.setEmpName(ename);
		log.setGoodsName(detail.getGoodsname());
		log.setStoreName(store.getName());
		log.setEmpuuid(eid);
		log.setGoodsuuid(detail.getGoodsuuid());
		log.setNum(detail.getNum());
		log.setOpertime(detail.getEndtime());
		log.setStoreuuid(storeuuid);
		log.setType(Storeoper.TYPE_OUT);
		storeoperMapper.insert(log);
		
		//**** ��4�� �Ƿ���Ҫ���¶�����״̬********
		
		//1. ������ѯ����
		Orderdetail queryParam = new Orderdetail();
		long oid=detail.getOrders_uuid();
		Orders orders=ordersMapper.get(oid);
		//2. ��ѯ�������Ƿ񻹴���״̬Ϊ0����ϸ
		queryParam.setState(Orderdetail.STATE_NOT_OUT);
		queryParam.setOrders_uuid(oid);
		//3. ���� getCount�������������Ƿ����״̬Ϊ0����ϸ
		long count = orderdetailMapper.getCount(queryParam);
		if(count == 0){
			//4. ���е���ϸ���Ѿ������ˣ���Ҫ���¶�����״̬���رն���
			orders.setState(Orders.STATE_OUT);
			orders.setEndtime(detail.getEndtime());
			orders.setEnder(eid);
			orders.setEnderName(ename);
			ordersMapper.update(orders);
		}
	}
	
}
