package com.minihelpdesk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minihelpdesk.dto.user.AuthRequest;
import com.minihelpdesk.dto.user.AuthResponse;
import com.minihelpdesk.dto.user.RegisterRequest;
import com.minihelpdesk.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
	    return authService.register(request);
	}

	@PostMapping("/login")
	public AuthResponse login(@Valid @RequestBody AuthRequest request) {
	    return authService.login(request);
	}

	@GetMapping("/me")
	public String me() {
		return "You are authenticated!";
	}
}
