package io.github.kji6252.springvue.mapper;

import io.github.kji6252.springvue.controller.vm.UserInfoVM;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserMapperTest {

    @Test
    void testUserToVM() {
        //given
        UserDetails userDetails = User.builder()
                                      .username("user")
                                      .password("user")
                                      .roles("USER")
                                      .build();
        //when
        UserInfoVM userInfoVM = UserMapper.INSTANCE.userToVM(userDetails);
        //then
        assertEquals("user", userInfoVM.getUsername());
        assertTrue(userInfoVM.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }
}