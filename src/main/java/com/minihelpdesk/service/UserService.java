package com.minihelpdesk.service;

import com.minihelpdesk.dto.user.UserDto;
import java.util.Optional;

public interface UserService {

    Optional<UserDto> get(Long id);

}
