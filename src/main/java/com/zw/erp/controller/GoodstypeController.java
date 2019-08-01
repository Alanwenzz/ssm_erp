package com.zw.erp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Goodstype;
import com.zw.erp.service.GoodstypeService;

@Controller
public class GoodstypeController {
	@Autowired
	public GoodstypeService goodstypeService;
	
	@RequestMapping("goodstype")
	public String goodstypetype_main() {
		return "goodstype";
	}
	
	//���
	@ResponseBody
	@RequestMapping("goodstype_add")
	public Map<String, Object> add(Goodstype goodstype){
		Map<String, Object> rtn;
		try {
			goodstypeService.add(goodstype);
			rtn=ajaxReturn(true, "��ӳɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "���ʧ��");
		}
		return rtn;
	}
	//ɾ��
	@ResponseBody
	@RequestMapping("goodstype_delete")
	public Map<String, Object> delete(long uuid){
		Map<String, Object> rtn;
		try {
			goodstypeService.delete(uuid);
			rtn=ajaxReturn(true, "ɾ���ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "ɾ��ʧ��");
		}
		return rtn;
	}
	//�޸�
	@ResponseBody
	@RequestMapping("goodstype_update")
	public Map<String, Object> update(Goodstype goodstype){
		Map<String, Object> rtn;
		try {
			goodstypeService.update(goodstype);
			rtn=ajaxReturn(true, "���³ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "����ʧ��");
		}
		return rtn;
	}
	
	//����id��ѯ������Ϣ
	@ResponseBody
	@RequestMapping("goodstype_get")
	public Goodstype get(long uuid){
		Goodstype goodstype=goodstypeService.get(uuid);
		return goodstype;
	}
	
	//��ѯ
	@ResponseBody
	@RequestMapping("goodstype_getList")
	public List<Goodstype> getList(Goodstype goodstype){
		List<Goodstype> ld=goodstypeService.findByCondition(goodstype);
		return ld;
	}
	
	//ajax����
	public Map<String, Object> ajaxReturn(boolean success,String message){
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
}
