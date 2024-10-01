package ru.kata.spring.boot_security.demo.entity;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    private String username;

    @Nullable
    private String role;



//    @Transient
//    @ManyToMany(mappedBy = "users_roles")
//    private List<User> users;


    @Override
    public String getAuthority() {
        return role;
    }
}

