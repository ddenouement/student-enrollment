package com.example.studentenrollment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table
@Data
@Builder
public class Teacher implements UserDetails {
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String title;

    @Column(unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "fk_faculty_id", nullable = false)
    private Faculty faculty;

    @ManyToMany(mappedBy = "curatedBy")
    private Set<Course> curatedCourses;

    @Column
    @JsonIgnore
    private String hashedPassword;

    @ElementCollection(targetClass = Authority.class)
    @JoinTable(name = "Teacher_Authorities", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private List<Authority> authorities;

    public static class TeacherBuilder {

        private List<Authority> authorities = Arrays.asList(
                Authority.CREATE_COURSE,
                Authority.READ_STUDENTS_ENROLLED_TO_COURSE,
                Authority.READ_STUDENTS_LIST,
                Authority.READ_TEACHERS_LIST,
                Authority.MODIFY_COURSE);
        public TeacherBuilder password(String password){
            this.hashedPassword=ENCODER.encode(password);
            return this;
        }
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
