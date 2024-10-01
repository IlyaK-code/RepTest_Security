package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/admin/users")
public class UsersController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public UsersController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsServiceImpl = userDetailsService;
    }

    @GetMapping()
    public String show(Model model) {
//        model.addAttribute("users", userDetailsServiceImpl.findAll());
        return "users";
    }

    @GetMapping("/user")
    public String index(@RequestParam("id") Long id, Model model) {
//        User user = userDetailsServiceImpl.findById(id);
//        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
//        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) throws IOException {
//        userDetailsServiceImpl.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
//        userDetailsServiceImpl.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String edit(Model model, @RequestParam("id") Long id) {
//        model.addAttribute("user", userDetailsServiceImpl.findById(id));
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
//        userDetailsServiceImpl.update(id, user);
        return "redirect:/users";
    }
}