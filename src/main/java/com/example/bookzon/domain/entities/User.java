package com.example.bookzon.domain.entities;

import com.example.bookzon.domain.enums.AuthProvider;
import com.example.bookzon.domain.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails, OAuth2User {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String username;
    private String password;
    private UserRole role;
    private String name;
    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;
    @Transient
    private Map<String, Object> attributes;

    public User(String username, String password, UserRole role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public User(UUID id){
        this.id = id;
    }

    public User(String username, String name, String email, String password, UserRole userRole) {
        this.username = username;
        this.name = name;
        this.email  = email;
        this.password = password;
        this.role = userRole;
    }


    @Override

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getPassword(){
      return  password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    @Override
    public String getName() {
        return null;
    }

}