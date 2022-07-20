package io.github.kji6252.springvue.controller;

import jdk.internal.org.jline.utils.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AccountController {


    @GetMapping("/account")
    public String getAccount(HttpServletRequest request) {
        return request.getRemoteUser();
    }

}
