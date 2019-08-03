package com.zw.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Storealert;
import com.zw.erp.service.StorealertService;

@Controller
public class StorealertController {
	@Autowired
	private StorealertService storealertService;
	
	@RequestMapping("storealert")
	public String Storealert() {
		return "storealert";
	}
	
	@RequestMapping("storedetail_storealertList")
	@ResponseBody
	public List<Storealert> storealertList(){
		return storealertService.getStorealertList();
	}
	
	//·¢ËÍÓÊ¼þ
	@RequestMapping("storedetail_sendStorealerMail")
	@ResponseBody
	public int sendStorealerMail() {
		try {
			storealertService.sendStorealertMail();
			return 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
	}
}
