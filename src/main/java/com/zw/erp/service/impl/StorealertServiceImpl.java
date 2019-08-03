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
	 * ���Ϳ��Ԥ���ʼ�
	 */
	public void sendStorealertMail() throws Exception {
		// TODO Auto-generated method stub
		String subject="��� Ԥ��   ʱ��:[time]";
		String text="��!��[count]����Ʒ�Ѿ�Ԥ���ˣ����½ERPϵͳ�鿴";
		String to="364946726@qq.com";
		//��ȡ���Ԥ���б�
		List<Storealert> storealertList = storedetailMapper.getStorealertList();
		//��ȡ���Ԥ����Ʒ�ĸ���
		int size = null == storealertList?0:storealertList.size();
		if(size > 0){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//subject = ���Ԥ��_ʱ��:[time] => ���Ԥ��_ʱ��:2017-05-04 10:42:45
				String sub = subject.replace("[time]", sdf.format(new Date()));			
				//���Ԥ��_ʱ��:%s
				//String sub = String.format(subject, sdf.format(new Date()));
				//text=�𾴵Ŀͻ�����[count]����Ʒ�Ѿ�Ԥ���ˣ����½����ERP3.1ϵͳ�鿴
				//text=�𾴵Ŀͻ�����size����Ʒ�Ѿ�Ԥ���ˣ����½����ERP3.1ϵͳ�鿴
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
