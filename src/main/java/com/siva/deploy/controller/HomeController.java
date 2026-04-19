package com.siva.deploy.controller;

import com.siva.deploy.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final UserService userService;

    @Value("${server.port:8085}")
    private String serverPort;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("serverPort", serverPort);
        return "index";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@Valid @ModelAttribute("userForm") UserForm userForm,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (userService.usernameExists(userForm.getUsername())) {
            bindingResult.rejectValue("username", "error.userForm", "Username already exists");
            return "signup";
        }

        userService.register(userForm.getUsername(), userForm.getPassword());
        model.addAttribute("serverPort", serverPort);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@Valid @ModelAttribute("userForm") UserForm userForm,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        if (!userService.authenticate(userForm.getUsername(), userForm.getPassword())) {
            bindingResult.reject("loginError", "Invalid username or password");
            return "login";
        }

        model.addAttribute("serverPort", serverPort);
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("username", "guest");
        return "dashboard";
    }
}
