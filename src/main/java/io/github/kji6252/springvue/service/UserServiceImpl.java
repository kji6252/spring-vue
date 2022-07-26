package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.service.dto.UserAndPasswordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserAndPasswordDTO userAndPasswordDTO) {
        userDetailsManager.createUser(User.builder()
                                          .username(userAndPasswordDTO.getUsername())
                                          .password(passwordEncoder.encode(userAndPasswordDTO.getPassword()))
                                          .roles("USER")
                                          .build()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userDetailsManager.loadUserByUsername(username);
    }
}
