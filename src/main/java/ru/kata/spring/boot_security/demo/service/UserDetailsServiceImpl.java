package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    @Qualifier("BCrypto")
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.print("User not found");
            throw new UsernameNotFoundException("User not found");
        }
//        return new UserPrincipal(user);
//        return new org.springframework.security.core.userdetails
//                .User(user.getUsername(), user.getPassword(), getAuthorities(user.getRoles()));

//        return userRepository.findByUsername(name);
        return user;
    }
//
    /*private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getAuthority()))
                .collect(Collectors.toList());
    }*/

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, User user) {
        Optional<User> userFromDB = userRepository.findById(id)
                .stream().peek(u -> {
                    u.setUsername(user.getUsername());
                    u.setPassword(passwordEncoder.encode(user.getPassword()));
                }).findFirst();
        userRepository.save(userFromDB.get());
    }
}
