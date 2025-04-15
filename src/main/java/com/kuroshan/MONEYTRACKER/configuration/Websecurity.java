package com.kuroshan.MONEYTRACKER.configuration;

import com.kuroshan.MONEYTRACKER.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Websecurity{
    @Autowired
    private final UserService userService;

    public Websecurity(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                        request-> request
                                .requestMatchers("register").permitAll()
                                .requestMatchers("login").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/user/**").hasRole("USER")
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .logout(LogoutConfigurer::permitAll);
                return httpSecurity.build();

//                i can use this also
//                http
//    .authorizeRequests(authorizeRequests -> authorizeRequests
//                .antMatchers(HttpMethod.GET, "/api/public/**").permitAll() // Allow GET requests to public API
//                .antMatchers(HttpMethod.POST, "/api/admin/**").hasRole("ADMIN") // Allow POST requests to admin API
//                .anyRequest().authenticated()
//        );

    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }
}
