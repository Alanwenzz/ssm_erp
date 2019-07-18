package com.zw.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {
	
	@RequestMapping("orders")
	public String orders() {
		return "orders";
	}
	
	@RequestMapping("orders_add")
	public String orders_a() {
		return "orders_add";
	}
}
