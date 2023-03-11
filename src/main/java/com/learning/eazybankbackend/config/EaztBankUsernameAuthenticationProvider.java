package com.learning.eazybankbackend.config;

import com.learning.eazybankbackend.model.Authority;
import com.learning.eazybankbackend.model.Customer;
import com.learning.eazybankbackend.repository.CustomerRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class EaztBankUsernameAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public EaztBankUsernameAuthenticationProvider(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        Optional<Customer> customerOptional = customerRepository.findByEmail(username);

        if(customerOptional.isPresent()){
            if(passwordEncoder.matches(pwd,customerOptional.get().getPwd())){
                return new UsernamePasswordAuthenticationToken(username, pwd,getGrantedAuthority(customerOptional.get()
                        .getAuthoritySet()));
            }
            else{
                throw new BadCredentialsException("Invalid Password");
            }
        }
        else{
            throw new BadCredentialsException("No User Registered with this details");
        }
    }

    private Set<GrantedAuthority> getGrantedAuthority(Set<Authority> authorities){
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        authorities.forEach(authority -> grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName())));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
