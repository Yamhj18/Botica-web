package com.epiis.apirfbvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epiis.apirfbvc.business.BusinessInventory;
import com.epiis.apirfbvc.dto.response.ResponseInventoryIncome;

@RestController
@RequestMapping(path = "inventory")
public class InventoryController {
	private final BusinessInventory businessInventory;

	public InventoryController(BusinessInventory businessInventory) {
		this.businessInventory = businessInventory;
	}
	
	@GetMapping(path = "getall")
	public ResponseEntity<ResponseInventoryIncome> listIncome(){
		return ResponseEntity.ok(businessInventory.getIncomes());
	}
}
