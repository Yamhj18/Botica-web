package com.epiis.apirfbvc.business;

<<<<<<< HEAD
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.epiis.apirfbvc.dto.request.RequestCategoryInsert;
import com.epiis.apirfbvc.dto.response.ResponseCategoryGetAll;
import com.epiis.apirfbvc.dto.response.ResponseCategoryInsert;
import com.epiis.apirfbvc.entity.EntityCategory;
import com.epiis.apirfbvc.repository.RepositoryCategory;
import com.epiis.apirfbvc.repository.RepositoryProduct;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.epiis.apirfbvc.dto.response.ResponseCategoryGetAll;
import com.epiis.apirfbvc.entity.EntityCategory;
import com.epiis.apirfbvc.repository.RepositoryCategory;
>>>>>>> botica-web/dev-backend

@Service
public class BusinessCategory {
	private final RepositoryCategory repositoryCategory;
<<<<<<< HEAD
	private final RepositoryProduct repositoryProduct;

	public BusinessCategory(RepositoryCategory repositoryCategory, RepositoryProduct repositoryProduct) {
		this.repositoryCategory = repositoryCategory;
		this.repositoryProduct = repositoryProduct;
	}
	
	public ResponseCategoryInsert insert(RequestCategoryInsert request) throws IOException {
		ResponseCategoryInsert response = new ResponseCategoryInsert();
		
		EntityCategory entityCategory = new EntityCategory();
		
		if(repositoryCategory.existsByName(request.getName())) {
			response.listMessage.add("El nombre ya existe en el sistema.");
			return response;
		}
		
		entityCategory.setImage(request.getImage() == null || request.getImage().trim().isEmpty()
			        ? null
			        : request.getImage()
			);
		entityCategory.setIdCategory(UUID.randomUUID().toString());
		entityCategory.setName(request.getName());
		entityCategory.setStatus("activo");
		
		entityCategory.setCreatedAt(new java.sql.Date(new Date().getTime()));
		entityCategory.setUpdatedAt(entityCategory.getCreatedAt());

		repositoryCategory.save(entityCategory);
		
		response.success();
		response.listMessage.add("Registro realizado correctamente.");
		
		return response;
	}
	
	
=======

	public BusinessCategory(RepositoryCategory repositoryCategory) {
		this.repositoryCategory = repositoryCategory;
	}
	
>>>>>>> botica-web/dev-backend
	public ResponseCategoryGetAll getAll() {
		ResponseCategoryGetAll response = new ResponseCategoryGetAll();
		
		List<EntityCategory> listCategories = repositoryCategory.findAll();
		
		for(EntityCategory item: listCategories) {
			Map<String, String> data = new HashMap<>();
			
			data.put("idCategory", item.getIdCategory());
			data.put("image", item.getImage());
			data.put("name", item.getName());
			data.put("status", item.getStatus());
<<<<<<< HEAD
			data.put("totalProducts", String.valueOf(
			    repositoryProduct.countByCategory_IdCategory(item.getIdCategory())
			));
=======
>>>>>>> botica-web/dev-backend
	        data.put("createdAt", item.getCreatedAt().toString());
			
			response.getListCategories().add(data);
		}
		
		response.success();
		
		return response;
	}

}
