//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.kata.spring.boot_security.demo.entity.User;
//import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImpl;
//
//import java.security.Principal;
//
//@RestController
//public class MainController {
//
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsServiceImpl;
//
//
//
//    @GetMapping("/")
//    public String index() {
//        return "index";
//    }
//
//    @GetMapping("/user")
//    public String user(Principal principal, Model model) {
////        User user =(User) userDetailsServiceImpl.loadUserByUsername(principal.getName());
////        model.addAttribute("user", user);
//        return "user";
//    }
//
//}
