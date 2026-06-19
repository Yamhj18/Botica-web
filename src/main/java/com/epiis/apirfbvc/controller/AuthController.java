package com.epiis.apirfbvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.epiis.apirfbvc.business.BusinessAuth;
import com.epiis.apirfbvc.dto.request.RequestLogin;
import com.epiis.apirfbvc.dto.response.ResponseLogin;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "auth")
public class AuthController {
	private final BusinessAuth businessAuth;
	
	public AuthController(
			BusinessAuth businessAuth
	) {
		this.businessAuth = businessAuth;
	}
	
    @PostMapping(path = "login")
    public ResponseEntity<ResponseLogin> login(@Valid @RequestBody RequestLogin request) {
        ResponseLogin response = businessAuth.login(request);
        return ResponseEntity.ok(response);
    }
}