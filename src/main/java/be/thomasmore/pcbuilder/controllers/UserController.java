package be.thomasmore.pcbuilder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) return "redirect:/account";
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(Principal principal) {
        if (principal == null) return "redirect:/account";
        return "user/logout";
    }
}
