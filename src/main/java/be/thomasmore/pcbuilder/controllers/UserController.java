package be.thomasmore.pcbuilder.controllers;

import be.thomasmore.pcbuilder.models.Authority;
import be.thomasmore.pcbuilder.models.User;
import be.thomasmore.pcbuilder.repos.UserRepository;
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

import javax.sql.DataSource;
import java.security.Principal;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository users;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/login")
    public String login(Principal principal) {
        logger.info("Logging in: ");
        if (principal != null) {
            logger.info("User already logged in: {}", principal.getName());
            return "redirect:/account";
        }
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        logger.info("Logging out user.");

            request.logout();

        return "redirect:/user/login?logout";
    }

    @GetMapping("/register")
    public String register(Principal principal) {
        if (principal != null) {
            logger.info("User already registered: {}", principal.getName());
            return "redirect:/account";
        }
        return "user/register";
    }

    @PostMapping("/register")
    public String registerPost(HttpServletRequest request,
                               @RequestParam String username,
                               @RequestParam String password) throws ServletException {
        if (username == null || username.isBlank()) {
            logger.warn("Registration failed: username is blank.");
            return "redirect:/account";
        }
        if (jdbcUserDetailsManager.userExists(username)) {
            logger.warn("Registration failed: username {} already exists.", username);
            return "redirect:/account";
        }
        try {

//        save user
            User newUser = new User();
            Authority authority = new Authority();

            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setEnabled(true);
            authority.setAuthority("USER");
            authority.setUser(newUser);
            newUser.setAuthorities(List.of(authority));
            users.save(newUser); // Insert authority using JDBC

//        UserDetails user = org.springframework.security.core.userdetails.User
//                .withUsername(username)
//                .password(passwordEncoder.encode(password))
//                .roles("USER")
//                .build();
//        jdbcUserDetailsManager.createUser(user);

//        autologin:

            request.login(username, password);
            logger.info("User {} registered and logged in successfully.", username);

            return "redirect:/account";
        } catch (Exception e) {
            logger.error("Registration failed for username {}: {}", username, e.getMessage());
            return "redirect:/user/register?error=server-error";
        }
    }
}
