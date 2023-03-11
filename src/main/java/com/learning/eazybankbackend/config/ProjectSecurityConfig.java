package com.learning.eazybankbackend.config;

import com.learning.eazybankbackend.filter.AuthoritiesLoggingAfterFilter;
import com.learning.eazybankbackend.filter.AuthoritiesLoggingAtFilter;
import com.learning.eazybankbackend.filter.CsrfCookieFilter;
import com.learning.eazybankbackend.filter.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;


@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{

        CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        requestAttributeHandler.setCsrfRequestAttributeName("_csrf");

        http.securityContext().requireExplicitSave(false)
                .and()
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors()
                .configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                    corsConfiguration.setAllowCredentials(true);
                    corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                    corsConfiguration.setMaxAge(3600L);
                    return corsConfiguration;
                })
                .and()
                .csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer.csrfTokenRequestHandler(requestAttributeHandler)
                                .ignoringRequestMatchers("/contact","/register")
                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/myAccount")
                //.hasAuthority("VIEWACCOUNT")
                .hasRole("USER")
                .requestMatchers("/myBalance")
                //.hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
                .hasAnyRole("USER","ADMIN")
                .requestMatchers("/myLoans")
                //.hasAuthority("VIEWLOANS")
                .hasRole("USER")
                .requestMatchers("/myCards")
                //.hasAuthority("VIEWCARDS")
                .hasRole("USER")
                .requestMatchers("/user").authenticated()
                .requestMatchers("/notices","/contact","/register").permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();

        return http.build();

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
