package io.github.kji6252.springvue.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Import(SecurityProblemSupport.class)
public class WebSecurityConfig {

    private final SecurityProblemSupport problemSupport;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                    .headers().frameOptions().sameOrigin()
                .and()
                    .csrf().disable()
                    .exceptionHandling()
                        .authenticationEntryPoint(problemSupport)
                        .accessDeniedHandler(problemSupport)
                .and()
                    .formLogin()
                        .loginProcessingUrl("/api/authentication")
                        .successHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
                        .failureHandler((request, response, exception) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed"))
                        .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/api/logout")
                    .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
                    .permitAll()
                .and()
                    .authorizeRequests(authorize -> authorize
                        .mvcMatchers("/api/register").permitAll()
                        .mvcMatchers("/api/search").permitAll()
                        .mvcMatchers("/api/hot-keywords").permitAll()
                        .mvcMatchers("/h2-console/**").permitAll()
                        .mvcMatchers("/api/**").authenticated()
                    );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager users(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                               .username("user")
                               .password(passwordEncoder.encode("user"))
                               .roles("USER")
                               .build();
        UserDetails admin = User.builder()
                                .username("admin")
                                .password(passwordEncoder.encode("admin"))
                                .roles("USER", "ADMIN")
                                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
