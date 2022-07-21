package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.service.dto.UserAndPasswordDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    void registerUser(UserAndPasswordDTO userAndPasswordDTO);

    UserDetails loadUserByUsername(String username);
}
