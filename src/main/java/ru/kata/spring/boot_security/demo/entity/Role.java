package ru.kata.spring.boot_security.demo.entity;


import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    private String role;

  /*  @Nullable
    private String role;*/


    @Transient
    @ManyToMany/*(mappedBy = "users_roles")*/
    private List<User> users;

    public void setRole(@Nullable String name) {
        this.role = name;
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

    @Nullable
    public String getRole() {
        return role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}

