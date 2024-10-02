package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/", "/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .permitAll();
    }

//    @Bean(name = "BCrypto")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }



    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        UserDetails user =
                User.builder()
                        .username("user")
//                        .password("{bcrypt}$2a$12$bxGVTLZPr7vrBrMOLzTrdOwv63l7ZhrOGpaGwlVJBotaBywPFmU9m")
                        .password(passwordEncoder().encode("100"))
                        .authorities("USER")
                        .build();

        UserDetails admin =
                User.builder()
                        .username("admin")
//                        .password("{bcrypt}$2a$12$bxGVTLZPr7vrBrMOLzTrdOwv63l7ZhrOGpaGwlVJBotaBywPFmU9m")
                        .password(passwordEncoder().encode("100"))
                        .authorities("ADMIN", "USER")
                        .build();
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        if (!jdbcUserDetailsManager.userExists(user.getUsername())) {
            jdbcUserDetailsManager.createUser(user);
        }
        if (!jdbcUserDetailsManager.userExists(admin.getUsername())) {
            jdbcUserDetailsManager.createUser(admin);
        }
        return jdbcUserDetailsManager;
    }
}