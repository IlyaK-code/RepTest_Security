package ru.kata.spring.boot_security.demo.entity;


import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "authorities")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    private String authority;

    @Nullable
    private String username;



    @Transient
    @ManyToMany/*(mappedBy = "users_roles")*/
    private List<User> users;

    @Nullable
    public String getUsername() {
        return username;
    }

    public void setUsername(@Nullable String username) {
        this.username = username;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(@Nullable String name) {
        this.authority = name;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}

