package be.thomasmore.pcbuilder.controllers;

import be.thomasmore.pcbuilder.models.Authority;
import be.thomasmore.pcbuilder.models.User;
import be.thomasmore.pcbuilder.repos.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

/**
 * Controller for managing user-related actions such as login, logout, and registration.
 * This controller handles requests related to user authentication and account management.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository users; // Repository for managing User entities.

    @Autowired
    private PasswordEncoder passwordEncoder; // Encoder for hashing passwords securely.

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager; // Spring Security manager for JDBC-based user details.

    private final Logger logger = LoggerFactory.getLogger(UserController.class); // Logger for recording activities.

    /**
     * Handles the login page request.
     * If the user is already logged in, they are redirected to their account page.
     *
     * @param principal Represents the authenticated user, if any.
     * @return The login view or redirect to account page if already logged in.
     */
    @GetMapping("/login")
    public String login(Principal principal) {
        logger.info("Logging in: ");
        if (principal != null) {
            logger.info("User already logged in: {}", principal.getName());
            return "redirect:/account"; // Redirects if the user is already logged in.
        }
        return "user/login"; // Returns the login page.
    }

    /**
     * Handles the logout action for a user.
     *
     * @param request The HTTP servlet request to manage logout.
     * @return Redirects to the login page with a logout flag.
     * @throws ServletException If an error occurs during logout.
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        logger.info("Logging out user.");
        request.logout(); // Logs the user out.
        return "redirect:/user/login?logout"; // Redirects to login page after logout.
    }

    /**
     * Handles the register page request.
     * If the user is already logged in, they are redirected to their account page.
     *
     * @param principal Represents the authenticated user, if any.
     * @return The register view or redirect to account page if already logged in.
     */
    @GetMapping("/register")
    public String register(Principal principal) {
        if (principal != null) {
            logger.info("User already registered: {}", principal.getName());
            return "redirect:/account"; // Redirects if the user is already registered and logged in.
        }
        return "user/register"; // Returns the register page.
    }

    /**
     * Handles the registration of a new user via a POST request.
     *
     * @param request  The HTTP servlet request for managing login post-registration.
     * @param username The username entered by the user.
     * @param password The raw password entered by the user.
     * @return Redirects to the account page on success, or back to the registration page on failure.
     * @throws ServletException If an error occurs during auto-login after registration.
     */
    @PostMapping("/register")
    public String registerPost(HttpServletRequest request,
                               @RequestParam String username,
                               @RequestParam String password) throws ServletException {
        if (username == null || username.isBlank()) {
            logger.warn("Registration failed: username is blank.");
            return "redirect:/account"; // Redirects if the username is invalid.
        }
        if (jdbcUserDetailsManager.userExists(username)) {
            logger.warn("Registration failed: username {} already exists.", username);
            return "redirect:/account"; // Redirects if the username is already taken.
        }
        try {
            // Nieuwe gebruiker aanmaken
            User newUser = new User();
            Authority authority = new Authority();

            // Vul de gebruiker attributen in
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password)); // Versleutelt het wachtwoord
            newUser.setEnabled(true);

            // Stel autoriteit in
            authority.setAuthority("USER");
            authority.setUser(newUser);
            newUser.setAuthorities(List.of(authority)); // Koppelt autoriteiten aan de gebruiker

            users.save(newUser); // Slaat de nieuwe gebruiker en autoriteit op in de database

            // Automatisch inloggen na registratie
            request.login(username, password);
            logger.info("User {} registered and logged in successfully.", username);

            return "redirect:/account"; // Redirect naar het account-overzicht na succesvolle registratie
        } catch (Exception e) {
            logger.error("Registration failed for username {}: {}", username, e.getMessage());
            return "redirect:/user/register?error=server-error"; // Foutmelding bij onverwachte serverfout
        }
    }
}

