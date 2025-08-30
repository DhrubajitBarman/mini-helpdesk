package com.minihelpdesk.service;

import com.minihelpdesk.config.JwtTokenProvider;
import com.minihelpdesk.dto.user.AuthRequest;
import com.minihelpdesk.dto.user.AuthResponse;
import com.minihelpdesk.dto.user.RegisterRequest;
import com.minihelpdesk.entity.Role;
import com.minihelpdesk.entity.User;
import com.minihelpdesk.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider tokenProvider;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
	}

	public AuthResponse register(RegisterRequest request) {
		User user = new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(Role.valueOf(request.getRole().toUpperCase()));

		userRepository.save(user);

		String token = tokenProvider.generateToken(user.getUsername(), user.getRole().name());
		return new AuthResponse(token, user.getRole().name());
	}

	public AuthResponse login(AuthRequest request) {
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}

		String token = tokenProvider.generateToken(user.getUsername(), user.getRole().name());
		return new AuthResponse(token, user.getRole().name());
	}
}
