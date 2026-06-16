package com.epiis.apirfbvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epiis.apirfbvc.business.BusinessCategory;
import com.epiis.apirfbvc.dto.response.ResponseCategoryGetAll;

@RestController
@RequestMapping(path = "categories")
public class CategoryController {
	private final BusinessCategory businessCategory;

	public CategoryController(BusinessCategory businessCategory) {
		this.businessCategory = businessCategory;
	}
	
	@GetMapping(path = "getall")
	public ResponseEntity<ResponseCategoryGetAll> listCategories(){
		return ResponseEntity.ok(businessCategory.getAll());
	}
	
}
