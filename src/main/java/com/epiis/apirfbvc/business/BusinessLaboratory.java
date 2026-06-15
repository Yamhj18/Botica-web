package com.epiis.apirfbvc.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.epiis.apirfbvc.dto.response.ResponseLaboratoryGetAll;
import com.epiis.apirfbvc.entity.EntityLaboratory;
import com.epiis.apirfbvc.repository.RepositoryLaboratory;

@Service
public class BusinessLaboratory {
	private final RepositoryLaboratory repositoryLaboratory;

	public BusinessLaboratory(RepositoryLaboratory repositoryLaboratory) {
		this.repositoryLaboratory = repositoryLaboratory;
	}
	
	public ResponseLaboratoryGetAll getAll() {
		ResponseLaboratoryGetAll response = new ResponseLaboratoryGetAll();
		
		List<EntityLaboratory> listLaboratories = repositoryLaboratory.findAll();
		
		for(EntityLaboratory item: listLaboratories) {
			Map<String, String> data = new HashMap<>();
			
			data.put("idLaboratory", item.getIdLaboratory());
			data.put("image", item.getImage());
			data.put("name", item.getName());
			data.put("status", item.getStatus());
	        data.put("createdAt", item.getCreatedAt().toString());

			response.getListLaboratories().add(data);
		}
		
		response.success();
		
		return response;
	}
	
}
