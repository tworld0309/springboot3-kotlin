/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package com.boot3kotlin.config;

import jakarta.servlet.DispatcherType
import org.springframework.cglib.core.Customizer
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests((requests) -> requests
//        .requestMatchers("/", "/home").permitAll()
//            .anyRequest().authenticated()
//        )
//        .formLogin((form) -> form
//        .loginPage("/login")
//            .permitAll()
//        )
//        .logout((logout) -> logout.permitAll());
//
//        return http.build();
//    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .csrf{
                it.disable()
            }
            .cors{
                it.disable()
            }
            .authorizeHttpRequests{
                it.requestMatchers("/info").permitAll()
                it.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                it.anyRequest().authenticated()
            }
            .formLogin{
                it.defaultSuccessUrl("/login/success", true).permitAll()
            }
            .logout(org.springframework.security.config.Customizer.withDefaults())
//            .requestMatchers(
//                "/api/v1/auth/**",
//                "/swagger-ui/**",
//                "/swagger-ui.html"
//            )
//            .permitAll()
//            .requestMatchers(HttpMethod.GET, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_READ.name, Permission.MANAGER_READ.name)
//            .requestMatchers(HttpMethod.POST, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_CREATE.name, Permission.MANAGER_CREATE.name)
//            .requestMatchers(HttpMethod.PUT, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name, Permission.MANAGER_UPDATE.name)
//            .requestMatchers(HttpMethod.DELETE, "/api/v1/management/**")
//            .hasAnyAuthority(Permission.ADMIN_DELETE.name, Permission.MANAGER_DELETE.name)
            /*
            .requestMatchers("/api/v1/admin/ **").hasRole(ADMIN.name())
            .requestMatchers(GET, "/api/v1/admin/ **").hasAuthority(ADMIN_READ.name())
            .requestMatchers(POST, "/api/v1/admin/ **").hasAuthority(ADMIN_CREATE.name())
            .requestMatchers(PUT, "/api/v1/admin/ **").hasAuthority(ADMIN_UPDATE.name())
            .requestMatchers(DELETE, "/api/v1/admin/ **").hasAuthority(ADMIN_DELETE.name())
            */
//            .anyRequest()
//            .authenticated()
//            .and()
//            .sessionManagement()
            //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            //.and()
            //.authenticationProvider(authenticationProvider)
            //.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
//            .logout()
//            .logoutUrl("/api/v1/auth/logout")
            //.addLogoutHandler(logoutService)
            //.logoutSuccessHandler(LogoutSuccessHandler { request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication? -> SecurityContextHolder.clearContext() })
        return http.build()
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//        User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    fun userDetailsService(): UserDetailsService{
        val user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build()

        val admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("password")
            .roles("ADMIN")
            .build()

        return InMemoryUserDetailsManager(user, admin)
    }


}

