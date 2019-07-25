package com.zw.erp.mapper;


import org.apache.ibatis.annotations.Param;

import com.zw.erp.pojo.Menu;

public interface MenuMapper {
	public Menu get(String menuid);
	public void deleteAllUmenu(long rid);
	public void insertUmenu(@Param("rid")long uuid,@Param("mid")String menuid);
}
