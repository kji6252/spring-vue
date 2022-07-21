package io.github.kji6252.springvue.service.dto;

import lombok.Value;

import javax.validation.constraints.Size;

@Value
public class UserAndPasswordDTO {

    @Size(min = 1, max = 50)
    String username;

    @Size(min = 4, max = 50)
    String password;
}
