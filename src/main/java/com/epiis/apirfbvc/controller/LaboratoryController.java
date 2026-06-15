package com.epiis.apirfbvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epiis.apirfbvc.business.BusinessLaboratory;
import com.epiis.apirfbvc.dto.response.ResponseLaboratoryGetAll;

@RestController
@RequestMapping(path = "laboratories")
public class LaboratoryController {
	final private BusinessLaboratory businessLaboratory;

	public LaboratoryController(BusinessLaboratory businessLaboratory) {
		this.businessLaboratory = businessLaboratory;
	}
	
	@GetMapping(path = "getall")
	public ResponseEntity<ResponseLaboratoryGetAll> listCategories(){
		return ResponseEntity.ok(businessLaboratory.getAll());
	}
	
}
