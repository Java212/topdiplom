package ru.top.java212.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import ru.top.java212.model.Role;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/slick/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/categories/**").permitAll()
                        .requestMatchers( HttpMethod.POST, "/users/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/sale", "/myOrder").hasRole(Role.ROLE_TENANT)
                        .requestMatchers("/sale", "/myOrder").hasRole(Role.ROLE_OWNER)
                        .anyRequest().authenticated()
                )
                
                .formLogin(formLogin -> formLogin
                        .loginPage("/?authModal")
                        .permitAll()
                        .successForwardUrl("/myOrder")
                        .failureForwardUrl("/login_error")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                        .logoutSuccessUrl("/?authModal")
                );
        
        return http.build();

    }

}
