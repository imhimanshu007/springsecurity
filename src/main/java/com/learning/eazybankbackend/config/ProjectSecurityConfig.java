package com.learning.eazybankbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
                .requestMatchers("/notices","/contact","/register").permitAll()
                .and().formLogin()
                .and().httpBasic();

        return http.build();

        /*Configuration to Deny all the request*/
       /* http.authorizeHttpRequests().anyRequest().denyAll()
                .and().formLogin()
                .and().httpBasic();

        return http.build();*/
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*@Bean
     *//*JDBC User Details Manager*//*
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }*/

    /*Creating Users with In-Memory User Details Approach 1
    * Not recommended for Production*/
   /* @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("Himanshu")
                .password("Himanshu@2411")
                .authorities("admin")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user@123")
                .authorities("user")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }*/

    /*Creating Users with In-Memory User Details Approach 2
     * Not recommended for Production*/
 /*   @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails admin = User.builder()
                .username("Himanshu")
                .password("Himanshu@2411")
                .authorities("admin")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password("user@123")
                .authorities("user")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }*/


}
