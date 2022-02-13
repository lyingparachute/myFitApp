package com.infoshare.myfitwebapp.controller;

import com.infoshare.myfitwebapp.model.User;
import com.infoshare.myfitwebapp.model.UserLogin;
import com.infoshare.myfitwebapp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getUser() {
        return "user";
    }

    @PostMapping("/goToUserEdit")
    public String goToEdit() {
        return "redirect:edit";
    }

    @GetMapping("/edit")
    public String getUpdate(Authentication authentication, Model model) {
        if (authentication != null) {
            UserLogin userLogin = userService.load(authentication.getName());
            User user = userLogin.getUser();
            model.addAttribute("user", user);
        }
        return "edit-user";
    }

    }
