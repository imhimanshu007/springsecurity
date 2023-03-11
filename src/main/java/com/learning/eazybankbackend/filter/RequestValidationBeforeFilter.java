package com.learning.eazybankbackend.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class RequestValidationBeforeFilter implements Filter {
    public static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
    private Charset credentialCharset = StandardCharsets.UTF_8;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;

        String header = request1.getHeader(AUTHORIZATION);

        if(header != null){
            header = header.trim();
            if(StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)){
                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decoded;
                try{
                    decoded = Base64.getDecoder().decode(base64Token);
                    String token = new String(decoded, credentialCharset);
                    int delim = token.indexOf(":");
                    if(delim == -1){
                        throw new BadCredentialsException("Invalid Basic Authentication Token");
                    }
                    String email  = token.substring(0,delim);
                    if(email.toLowerCase().contains("test")){
                        response1.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }
                }catch (IllegalArgumentException exception){
                    throw new BadCredentialsException("Failed to decode basic authentication token");
                }
            }
        }
        chain.doFilter(request1, response1);
    }
}
