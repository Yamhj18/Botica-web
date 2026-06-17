package com.epiis.apirfbvc.business;

<<<<<<< HEAD
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.epiis.apirfbvc.dto.request.RequestLaboratoryInsert;
import com.epiis.apirfbvc.dto.response.ResponseLaboratoryGetAll;
import com.epiis.apirfbvc.dto.response.ResponseLaboratoryInsert;
import com.epiis.apirfbvc.entity.EntityLaboratory;
import com.epiis.apirfbvc.repository.RepositoryLaboratory;
import com.epiis.apirfbvc.repository.RepositoryProduct;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.epiis.apirfbvc.dto.response.ResponseLaboratoryGetAll;
import com.epiis.apirfbvc.entity.EntityLaboratory;
import com.epiis.apirfbvc.repository.RepositoryLaboratory;
>>>>>>> botica-web/dev-backend

@Service
public class BusinessLaboratory {
	private final RepositoryLaboratory repositoryLaboratory;
<<<<<<< HEAD
    private final RepositoryProduct repositoryProduct;

    public BusinessLaboratory(RepositoryLaboratory repositoryLaboratory,
            RepositoryProduct repositoryProduct) {
		this.repositoryLaboratory = repositoryLaboratory;
		this.repositoryProduct = repositoryProduct;
	}
	
    public ResponseLaboratoryInsert insert(RequestLaboratoryInsert request) throws IOException {
		ResponseLaboratoryInsert response = new ResponseLaboratoryInsert();
		
		EntityLaboratory entityLaboratory = new EntityLaboratory();
		
		if(repositoryLaboratory.existsByName(request.getName())) {
			response.listMessage.add("El nombre ya existe en el sistema.");
			return response;
		}
		
		entityLaboratory.setImage(request.getImage() == null || request.getImage().trim().isEmpty()
			        ? null
			        : request.getImage()
			);
		entityLaboratory.setIdLaboratory(UUID.randomUUID().toString());
		entityLaboratory.setName(request.getName());
		entityLaboratory.setStatus("activo");
		
		entityLaboratory.setCreatedAt(new java.sql.Date(new Date().getTime()));
		entityLaboratory.setUpdatedAt(entityLaboratory.getCreatedAt());

		repositoryLaboratory.save(entityLaboratory);
		
		response.success();
		response.listMessage.add("Registro realizado correctamente.");
		
		return response;
	}
    
=======

	public BusinessLaboratory(RepositoryLaboratory repositoryLaboratory) {
		this.repositoryLaboratory = repositoryLaboratory;
	}
	
>>>>>>> botica-web/dev-backend
	public ResponseLaboratoryGetAll getAll() {
		ResponseLaboratoryGetAll response = new ResponseLaboratoryGetAll();
		
		List<EntityLaboratory> listLaboratories = repositoryLaboratory.findAll();
		
		for(EntityLaboratory item: listLaboratories) {
			Map<String, String> data = new HashMap<>();
			
			data.put("idLaboratory", item.getIdLaboratory());
			data.put("image", item.getImage());
			data.put("name", item.getName());
			data.put("status", item.getStatus());
<<<<<<< HEAD
			data.put("totalProducts", String.valueOf(
	                repositoryProduct.countByLaboratory_IdLaboratory(item.getIdLaboratory())
	            ));
=======
>>>>>>> botica-web/dev-backend
	        data.put("createdAt", item.getCreatedAt().toString());

			response.getListLaboratories().add(data);
		}
		
		response.success();
		
		return response;
	}
	
}
