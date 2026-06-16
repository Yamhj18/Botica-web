package com.epiis.apirfbvc.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.epiis.apirfbvc.dto.response.ResponseCategoryGetAll;
import com.epiis.apirfbvc.entity.EntityCategory;
import com.epiis.apirfbvc.repository.RepositoryCategory;

@Service
public class BusinessCategory {
	private final RepositoryCategory repositoryCategory;

	public BusinessCategory(RepositoryCategory repositoryCategory) {
		this.repositoryCategory = repositoryCategory;
	}
	
	public ResponseCategoryGetAll getAll() {
		ResponseCategoryGetAll response = new ResponseCategoryGetAll();
		
		List<EntityCategory> listCategories = repositoryCategory.findAll();
		
		for(EntityCategory item: listCategories) {
			Map<String, String> data = new HashMap<>();
			
			data.put("idCategory", item.getIdCategory());
			data.put("image", item.getImage());
			data.put("name", item.getName());
			data.put("status", item.getStatus());
	        data.put("createdAt", item.getCreatedAt().toString());
			
			response.getListCategories().add(data);
		}
		
		response.success();
		
		return response;
	}

}
