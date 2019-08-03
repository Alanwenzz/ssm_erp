package com.zw.erp.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zw.erp.mapper.StoredetailMapper;
import com.zw.erp.pojo.Storealert;
import com.zw.erp.service.StorealertService;
import com.zw.erp.util.MailUtil;

@Service
public class StorealertServiceImpl implements StorealertService{
	@Autowired
	private MailUtil mailUtil;
	@Autowired
	private StoredetailMapper storedetailMapper;
	
	public List<Storealert> getStorealertList() {
		// TODO Auto-generated method stub
		return storedetailMapper.getStorealertList();
	}
	
	
	/**
	 * 发送库存预警邮件
	 */
	public void sendStorealertMail() throws Exception {
		// TODO Auto-generated method stub
		String subject="库存 预警   时间:[time]";
		String text="亲!有[count]种商品已经预警了，请登陆ERP系统查看";
		String to="364946726@qq.com";
		//获取库存预警列表
		List<Storealert> storealertList = storedetailMapper.getStorealertList();
		//获取库存预警商品的个数
		int size = null == storealertList?0:storealertList.size();
		if(size > 0){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//subject = 库存预警_时间:[time] => 库存预警_时间:2017-05-04 10:42:45
				String sub = subject.replace("[time]", sdf.format(new Date()));			
				//库存预警_时间:%s
				//String sub = String.format(subject, sdf.format(new Date()));
				//text=尊敬的客户，有[count]种商品已经预警了，请登陆蓝云ERP3.1系统查看
				//text=尊敬的客户，有size种商品已经预警了，请登陆蓝云ERP3.1系统查看
				String.format(text, size);
				mailUtil.sendMail(to, sub, text.replace("[count]", String.valueOf(size)));
			} catch (MessagingException e) {
				e.printStackTrace();
				throw new Exception();
			}
		}else{
			throw new Exception();
		}
	}


}
