package io.github.kji6252.springvue.controller;

import io.github.kji6252.springvue.controller.vm.UserInfoVM;
import io.github.kji6252.springvue.mapper.UserMapper;
import io.github.kji6252.springvue.service.UserService;
import io.github.kji6252.springvue.service.dto.UserAndPasswordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AccountController {

    private final UserService userService;

    @GetMapping("/account")
    public UserInfoVM getAccount(HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getRemoteUser())) {
            throw new AccountControllerException("User could not be found");
        }

        return UserMapper.INSTANCE
                .userToVM(userService.loadUserByUsername(request.getRemoteUser()));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody UserAndPasswordDTO userAndPasswordDTO) {
        userService.registerUser(userAndPasswordDTO);
    }

    private static class AccountControllerException extends RuntimeException {
        private AccountControllerException(String message) {
            super(message);
        }
    }
}
