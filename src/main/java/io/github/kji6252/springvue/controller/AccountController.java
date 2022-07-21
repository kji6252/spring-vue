package io.github.kji6252.springvue.controller;

import io.github.kji6252.springvue.controller.vm.UserInfoVM;
import io.github.kji6252.springvue.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AccountController {

    private final UserDetailsManager userDetailsManager;

    @GetMapping("/account")
    public UserInfoVM getAccount(HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getRemoteUser())) {
            throw new AccountControllerException("User could not be found");
        }

        return UserMapper.INSTANCE
                .userToVM(userDetailsManager.loadUserByUsername(request.getRemoteUser()));
    }

    private static class AccountControllerException extends RuntimeException {
        private AccountControllerException(String message) {
            super(message);
        }
    }
}
