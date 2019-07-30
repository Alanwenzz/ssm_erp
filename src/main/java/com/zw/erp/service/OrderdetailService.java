package com.zw.erp.service;
import java.util.List;
import com.zw.erp.pojo.Orderdetail;

public interface OrderdetailService {
	public void add(Orderdetail orderdetail);
	public void delete(long uuid);
	public void update(Orderdetail orderdetail);
	public List<Orderdetail> findByCondition(Orderdetail orderdetail);
	public Orderdetail get(long uuid);
}
