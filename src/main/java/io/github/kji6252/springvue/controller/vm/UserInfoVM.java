package io.github.kji6252.springvue.controller.vm;

import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Value
public class UserInfoVM {

    String username;

    Collection<GrantedAuthority> authorities;
}
