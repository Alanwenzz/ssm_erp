package com.zw.erp.mapper;

import java.util.List;
import com.zw.erp.pojo.Supplier;

public interface SupplierMapper {
	public List<Supplier> findByCondition(Supplier supplier);
	public void insert(Supplier supplier);
	public void delete(long uuid);
	public void update(Supplier supplier);
	public Supplier get(long uuid);
	public List<Supplier> getByType(String type);
}
