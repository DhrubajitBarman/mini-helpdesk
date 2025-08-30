package com.minihelpdesk.service.impl;

import com.minihelpdesk.dto.user.UserDto;
import com.minihelpdesk.repository.UserRepository;
import com.minihelpdesk.service.UserService;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Optional<UserDto> get(Long id) {
		return userRepository.findById(id).map(user -> {
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setUsername(user.getUsername());
			dto.setEmail(user.getEmail());
			dto.setRole(user.getRole().name());
			return dto;
		});
	}
}
