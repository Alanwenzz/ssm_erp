package com.zw.erp.service;

import java.util.List;

import com.zw.erp.pojo.Supplier;

public interface SupplierService {
	public void add(Supplier supplier);
	public void delete(long uuid);
	public void update(Supplier supplier);
	public List<Supplier> findByCondition(Supplier supplier);
	public Supplier get(long uuid);
	public List<Supplier> getByType(String type);
}
