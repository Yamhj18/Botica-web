package com.epiis.apirfbvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epiis.apirfbvc.business.BusinessCustomer;
import com.epiis.apirfbvc.dto.response.ResponseCustomerGetAll;

@RestController
@RequestMapping(path = "customer")
public class CustomerController {
	private final BusinessCustomer businessCustomer;
	
	public CustomerController(BusinessCustomer businessCustomer) {
		this.businessCustomer = businessCustomer;
	}


	@GetMapping(path = "getall")
	public ResponseEntity<ResponseCustomerGetAll> listUsers() {
		return ResponseEntity.ok(businessCustomer.getAll());
	}
}
