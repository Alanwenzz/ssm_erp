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

	//入库
	public void doInStore(Long odid, Long storeuuid, long eid,String ename) {
		// TODO Auto-generated method stub
		//******** 第1步 更新商品明细**********
		//1. 获取明细信息
		Orderdetail detail = orderdetailMapper.get(odid);
		//2. 判断明细的状态，一定是未入库的
		if(!Orderdetail.STATE_NOT_IN.equals(detail.getState())){
			try {
				throw new Exception("不能重复入库");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//3. 修改状态为已入库
		detail.setState(Orderdetail.STATE_IN);
		//4. 入库时间
		detail.setEndtime(new Date());
		//5. 库管员
		detail.setEnder(eid);
		//6. 入到哪个仓库
		detail.setStoreuuid(storeuuid);
		orderdetailMapper.update(detail);
		
		//*******第2 步 更新商品仓库库存*********
		//1. 构建查询的条件
		Storedetail storedetail = new Storedetail();
		//获取仓库名称
		Store store=storeMapper.get(storeuuid);
		storedetail.setStoreName(store.getName());
		
		storedetail.setGoodsName(detail.getGoodsname());
		storedetail.setGoodsuuid(detail.getGoodsuuid());
		storedetail.setStoreuuid(storeuuid);
		
		//2. 通过查询 检查是否存在库存信息
		List<Storedetail> storeList = storedetailMapper.findByCondition(storedetail);
		if(storeList.size()>0){
			//存在的话，则应该累加它的数量
			long num = 0;
			if(null != storeList.get(0).getNum()){
				num = storeList.get(0).getNum().longValue();
			}
			storedetail.setNum(num + detail.getNum());
			storedetail.setUuid(storeList.get(0).getUuid());
			storedetailMapper.update(storedetail);
		}else{
			//不存在，则应该插入库存的记录
			storedetail.setNum(detail.getNum());
			storedetailMapper.insert(storedetail);
		}
		
		//****** 第3步 添加操作记录*****
		//1. 构建操作记录
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
		
		//**** 第4步 是否需要更新订单的状态********
		
		//1. 构建查询条件
		Orderdetail queryParam = new Orderdetail();
		long oid=detail.getOrders_uuid();
		Orders orders=ordersMapper.get(oid);
		//2. 查询订单下是否还存在状态为0的明细
		queryParam.setState(Orderdetail.STATE_NOT_IN);
		queryParam.setOrders_uuid(oid);
		//3. 调用 getCount方法，来计算是否存在状态为0的明细
		long count = orderdetailMapper.getCount(queryParam);
		if(count == 0){
			//4. 所有的明细都已经出库了，则要更新订单的状态，关闭订单
			orders.setState(Orders.STATE_END);
			orders.setEndtime(detail.getEndtime());
			orders.setEnder(eid);
			orders.setEnderName(ename);
			ordersMapper.update(orders);
		}
	}

	public void doOutStore(Long odid, Long storeuuid, long eid, String ename) {
		// TODO Auto-generated method stub
		//1. 获取明细信息
		Orderdetail detail = orderdetailMapper.get(odid);
		//2. 判断明细的状态，一定是未出库的
		if(!Orderdetail.STATE_NOT_OUT.equals(detail.getState())){
			try {
				throw new Exception("不能重复出库");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//3. 修改状态为已出库
		detail.setState(Orderdetail.STATE_OUT);
		//4. 出库时间
		detail.setEndtime(new Date());
		//5. 库管员
		detail.setEnder(eid);
		//6. 从哪个仓库出
		detail.setStoreuuid(storeuuid);
		orderdetailMapper.update(detail);
		
		//*******第2 步 更新商品仓库库存*********
		//1. 构建查询的条件
		Storedetail storedetail = new Storedetail();
		//获取仓库名称
		Store store=storeMapper.get(storeuuid);
		storedetail.setStoreName(store.getName());
		
		storedetail.setGoodsName(detail.getGoodsname());
		storedetail.setGoodsuuid(detail.getGoodsuuid());
		storedetail.setStoreuuid(storeuuid);
		
		//2. 通过查询 检查是否有库存
		List<Storedetail> storeList = storedetailMapper.findByCondition(storedetail);
		if(storeList.size()>0){
			//有库存  判断是否够
			Storedetail sd=storeList.get(0);
			sd.setNum(sd.getNum() - detail.getNum());
			if(sd.getNum() < 0){
				try {
					throw new Exception("库存不足");
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
				throw new Exception("库存不足");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//****** 第3步 添加操作记录*****
		//1. 构建操作记录
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
		
		//**** 第4步 是否需要更新订单的状态********
		
		//1. 构建查询条件
		Orderdetail queryParam = new Orderdetail();
		long oid=detail.getOrders_uuid();
		Orders orders=ordersMapper.get(oid);
		//2. 查询订单下是否还存在状态为0的明细
		queryParam.setState(Orderdetail.STATE_NOT_OUT);
		queryParam.setOrders_uuid(oid);
		//3. 调用 getCount方法，来计算是否存在状态为0的明细
		long count = orderdetailMapper.getCount(queryParam);
		if(count == 0){
			//4. 所有的明细都已经出库了，则要更新订单的状态，关闭订单
			orders.setState(Orders.STATE_OUT);
			orders.setEndtime(detail.getEndtime());
			orders.setEnder(eid);
			orders.setEnderName(ename);
			ordersMapper.update(orders);
		}
	}
	
}
