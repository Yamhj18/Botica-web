package com.epiis.apirfbvc.controller;

<<<<<<< HEAD
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
=======
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> botica-web/dev-backend
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epiis.apirfbvc.business.BusinessLaboratory;
<<<<<<< HEAD
import com.epiis.apirfbvc.dto.request.RequestLaboratoryInsert;
import com.epiis.apirfbvc.dto.response.ResponseLaboratoryGetAll;
import com.epiis.apirfbvc.dto.response.ResponseLaboratoryInsert;

import jakarta.validation.Valid;
=======
import com.epiis.apirfbvc.dto.response.ResponseLaboratoryGetAll;
>>>>>>> botica-web/dev-backend

@RestController
@RequestMapping(path = "laboratories")
public class LaboratoryController {
	final private BusinessLaboratory businessLaboratory;

	public LaboratoryController(BusinessLaboratory businessLaboratory) {
		this.businessLaboratory = businessLaboratory;
	}
	
<<<<<<< HEAD
	@PostMapping(path = "insert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseLaboratoryInsert> actionInsert(@Valid @ModelAttribute RequestLaboratoryInsert request, BindingResult bindingResult) {
		try {
			ResponseLaboratoryInsert response;
			
			if (bindingResult.hasErrors()) {
				response = new ResponseLaboratoryInsert();
				
				bindingResult.getAllErrors()
                .forEach(error -> response.listMessage.add(error.getDefaultMessage()));
				
				return ResponseEntity.ok(response);
			}
			
			response = businessLaboratory.insert(request);
			
			return ResponseEntity.ok(response);
		} catch(Exception e) {
			ResponseLaboratoryInsert response = new ResponseLaboratoryInsert();
	        response.exception();
	        response.listMessage.add(e.getMessage());
	        return ResponseEntity.ok(response);
		}
	}
	
=======
>>>>>>> botica-web/dev-backend
	@GetMapping(path = "getall")
	public ResponseEntity<ResponseLaboratoryGetAll> listCategories(){
		return ResponseEntity.ok(businessLaboratory.getAll());
	}
	
}
