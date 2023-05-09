package com.example.studentenrollment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@SuperBuilder(toBuilder = true)
@Entity
@Table
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements UserDetails {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    long id;

    @Column
    @JsonIgnore
    String hashedPassword;

    @Column(unique = true)
    String email;

    @ElementCollection(targetClass = Authority.class)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    List<Authority> authorities;
    //todo ENCODE PASSWORD
/*
    public static class UserBuilder{
        public UserBuilder password(String password){
            this.hashedPassword=ENCODER.encode(password);
            return this;
        }
    }*/

    User(String email, String hashedPassword, long id){
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.id = id;
        this.authorities = new ArrayList<>();
    }

    public String getUserType(){
        if (this instanceof Student){
            return "Student";
        }
        if (this instanceof Teacher){
            return "Teacher";
        }
        return "User";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(a -> new SimpleGrantedAuthority(a.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return email;
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
}
