package com.zw.erp.mapper;

import java.util.List;
import com.zw.erp.pojo.Orderdetail;

public interface OrderdetailMapper {
	public List<Orderdetail> findByCondition(Orderdetail orderdetail);
	public void insert(Orderdetail orderdetail);
	public void delete(long uuid);
	public void update(Orderdetail orderdetail);
	public Orderdetail get(long uuid);
}
