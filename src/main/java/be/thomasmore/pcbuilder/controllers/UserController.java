package be.thomasmore.pcbuilder.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/login")
    public String login(Principal principal) {
        logger.info("Logging in: " );
        if (principal != null) return "redirect:/account";
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(Principal principal) {
        logger.info("Logging out: " + principal.getName());
        if (principal == null) return "redirect:/account";
        return "user/logout";
    }

    @GetMapping("/register")
    public String register(Principal principal) {
        logger.info("Registering");
        if (principal != null) return "redirect:/account";
        return "user/register";
    }

    @PostMapping("/register")
    public String registerPost(Principal principal,
                               HttpServletRequest request,
                               @RequestParam String username,
                               @RequestParam String password) throws ServletException {
        if (principal != null) return "redirect:/account";
        if (username == null || username.isBlank()) return "redirect:/account";
        if (jdbcUserDetailsManager.userExists(username)) return "redirect:/account";

        UserDetails user = org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();
        jdbcUserDetailsManager.createUser(user);

        //autologin:
        request.login(username, password);

        return "redirect:/account";
    }
}
