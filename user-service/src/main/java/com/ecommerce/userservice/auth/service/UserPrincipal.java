package com.ecommerce.userservice.auth.service;

import com.ecommerce.userservice.auth.entity.UserAuth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private Long id; // This is what you need!
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String email, String username, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // This static method makes it easy to convert your User entity to a Principal
    public static UserPrincipal build(UserAuth user) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getEmail(),
                user.getPassword(),
                authorities
                //new ArrayList<>() // Add roles/authorities here if you have them
        );
    }

    public Long getId() { return id; }

    @Override
    public String getUsername() { return username; }
    public String getEmail(){return email;}

    @Override
    public String getPassword() { return password; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    // Boilerplate required by Spring Security
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
