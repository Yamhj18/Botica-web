package com.epiis.apirfbvc.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.epiis.apirfbvc.dto.response.ResponseCustomerGetAll;
import com.epiis.apirfbvc.dto.response.ResponseUserGetAll;
import com.epiis.apirfbvc.entity.EntityCustomer;
import com.epiis.apirfbvc.entity.EntityUser;
import com.epiis.apirfbvc.repository.RepositoryCustomer;

@Service
public class BusinessCustomer {
	private final RepositoryCustomer repositoryCustomer;

	public BusinessCustomer(RepositoryCustomer repositoryCustomer) {
		this.repositoryCustomer = repositoryCustomer;
	}
	
	public ResponseCustomerGetAll getAll() {
		ResponseCustomerGetAll response = new ResponseCustomerGetAll();
		
		List<EntityCustomer> listEntityCustomers = repositoryCustomer.findAll();
		
		for(EntityCustomer item: listEntityCustomers) {
			Map<String, String> data = new HashMap<>();
			
			data.put("idCustomer", item.getIdCustomer());
            data.put("documentType", item.getDocumentType());
            data.put("documentNumber", item.getDocumentNumber());
            data.put("name", item.getName());
            data.put("createdAt", item.getCreatedAt().toString());
            
			response.getListCustomers().add(data);
		}
		
		response.success();
		
		return response;
	}
}
