package com.zw.erp.mapper;

import java.util.List;
import com.zw.erp.pojo.Dep;

public interface DepMapper {
	public List<Dep> findByCondition(Dep dep);
	public void insert(Dep dep);
	public void delete(long uuid);
	public void update(Dep dep);
	public Dep get(long uuid);
}
