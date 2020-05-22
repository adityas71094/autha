package com.adiSuper.autha.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class PrincipalUser implements UserDetails {

    private User user;
    private Set<GrantedAuthority> authorities;
    private Set<String> simpleAuthorities;

    public PrincipalUser(User user, Set<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
        this.simpleAuthorities = authorities.stream()
                .map(authority -> authority.getAuthority()).collect(Collectors.toSet());
    }

    public void setAuthorities(Set<GrantedAuthority> authorities)
    {
        this.authorities=authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.getAccExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getAccLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.getCredExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }

    public UUID getId() {
        return user.getId();
    }

    public Set<String> getSimpleAuthorities()
    {
        return this.simpleAuthorities;
    }
}
