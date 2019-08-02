package com.zw.erp.service;
import java.util.List;
import com.zw.erp.pojo.Storeoper;

public interface StoreoperService {
	public void add(Storeoper storeoper);
	public void delete(long uuid);
	public void update(Storeoper storeoper);
	public List<Storeoper> findByCondition(Storeoper storeoper);
	public Storeoper get(long uuid);
	public long getCount();
	public List<Storeoper> getListByPage(int firstResult, int rows);
}
