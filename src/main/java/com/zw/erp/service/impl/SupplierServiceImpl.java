package com.zw.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zw.erp.mapper.SupplierMapper;
import com.zw.erp.pojo.Supplier;
import com.zw.erp.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{
	@Autowired
	private SupplierMapper supplierMapper;

	public void add(Supplier supplier) {
		// TODO Auto-generated method stub
		supplierMapper.insert(supplier);
	}

	public void delete(long uuid) {
		// TODO Auto-generated method stub
		supplierMapper.delete(uuid);
	}

	public void update(Supplier supplier) {
		// TODO Auto-generated method stub
		supplierMapper.update(supplier);;
	}

	public List<Supplier> findByCondition(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierMapper.findByCondition(supplier);
	}

	public Supplier get(long uuid) {
		// TODO Auto-generated method stub
		return supplierMapper.get(uuid);
	}

	public List<Supplier> getByType(String type) {
		// TODO Auto-generated method stub
		return supplierMapper.getByType(type);
	}
	
}
