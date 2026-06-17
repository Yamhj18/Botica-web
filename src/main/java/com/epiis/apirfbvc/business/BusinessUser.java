package com.epiis.apirfbvc.business;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.epiis.apirfbvc.dto.request.RequestUserInsert;
import com.epiis.apirfbvc.dto.response.ResponseUserGetAll;
import com.epiis.apirfbvc.dto.response.ResponseUserInsert;
import com.epiis.apirfbvc.entity.EntityUser;
import com.epiis.apirfbvc.repository.RepositoryUser;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class BusinessUser {
	private final RepositoryUser repositoryUser;
    private final PasswordEncoder passwordEncoder;
	
	public BusinessUser(
			RepositoryUser repositoryUser,
            PasswordEncoder passwordEncoder

	) {
		this.repositoryUser = repositoryUser;
		this.passwordEncoder = passwordEncoder;

	}
	
	public ResponseUserInsert insert(RequestUserInsert request) throws IOException {
		ResponseUserInsert response = new ResponseUserInsert();
		
		if (request.getDni() != null && repositoryUser.existsByDni(request.getDni())) {
	        response.error();
	        response.listMessage.add("El DNI ya se encuentra registrado en el sistema.");
	        return response;
	    }
	    
	    if (request.getEmail() != null && repositoryUser.existsByEmail(request.getEmail())) {
	        response.error();
	        response.listMessage.add("El correo electrónico ya se encuentra registrado en el sistema.");
	        return response;
	    }
		
		EntityUser entityUser = new EntityUser();
		
		entityUser.setImage(request.getImage() == null ? "avatar.png" : request.getImage());		
		entityUser.setIdUser(UUID.randomUUID().toString());
		entityUser.setDni(request.getDni());
		entityUser.setFirstName(request.getFirstName());
		entityUser.setSurName(request.getSurName());
		entityUser.setEmail(request.getEmail());
		entityUser.setCellPhone(request.getCellPhone());
		entityUser.setRole(request.getRole());
		entityUser.setStatus("activo");
		
		String password = request.getPassword();
		entityUser.setPassword(passwordEncoder.encode(password != null ? password : ""));
		
		entityUser.setCreatedAt(new java.sql.Date(new Date().getTime()));
		entityUser.setUpdatedAt(entityUser.getCreatedAt());

		repositoryUser.save(entityUser);
		
		response.success();
		response.listMessage.add("Registro realizado correctamente.");
		
		return response;
	}
	
	public ResponseUserGetAll getAll() {
		ResponseUserGetAll response = new ResponseUserGetAll();
		
		List<EntityUser> listEntityUsers = repositoryUser.findAll();
		
		for(EntityUser item: listEntityUsers) {
			Map<String, String> data = new HashMap<>();
			
			data.put("idUser", item.getIdUser());
			data.put("dni", item.getDni());		
			data.put("image", item.getImage());
			data.put("firstName", item.getFirstName());
			data.put("surName", item.getSurName());
			data.put("cellPhone", item.getCellPhone());		
			data.put("email", item.getEmail());
			data.put("role", item.getRole());
			data.put("status", item.getStatus());
			
			response.getListUsers().add(data);
		}
		
		response.success();
		
		return response;
	}

	public ResponseUserInsert updateUserStatus(String id, String newStatus) {
	    ResponseUserInsert response = new ResponseUserInsert();
	    try {
	    	String safeId = id != null ? id : "";
	        String safeStatus = newStatus != null ? newStatus.toLowerCase() : "activo";
	        
	        Optional<EntityUser> optionalUser = repositoryUser.findById(safeId);
	        
	        if (optionalUser.isPresent()) {
	            EntityUser user = optionalUser.get();
	            
	            user.setStatus(safeStatus);
	            user.setUpdatedAt(new java.sql.Date(new java.util.Date().getTime()));
	            
	            repositoryUser.save(user);
	            
	            response.success();
	            response.listMessage.add("Estado del usuario actualizado a '" + safeStatus + "' correctamente.");
	        } else {
	            response.error();
	            response.listMessage.add("No se encontró el usuario con el ID proporcionado.");
	        }
	    } catch (Exception e) {
	        response.exception();
	        response.listMessage.add("Error al actualizar el estado: " + e.getMessage());
	    }
	    
	    return response;
	}
}
