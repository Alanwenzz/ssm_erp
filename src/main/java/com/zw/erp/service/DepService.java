package com.zw.erp.service;
import java.util.List;
import com.zw.erp.pojo.Dep;

public interface DepService {
	public void add(Dep dep);
	public void delete(long uuid);
	public void update(Dep dep);
	public List<Dep> findByCondition(Dep dep);
	public Dep get(long uuid);
}
