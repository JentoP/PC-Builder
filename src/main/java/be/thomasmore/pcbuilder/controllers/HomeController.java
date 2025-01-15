package be.thomasmore.pcbuilder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.logging.Logger;

@Controller
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class.getName());

    @GetMapping({"/", "/home"})
    public String Home(Model model, Principal principal) {
        final String loginName = principal != null ? principal.getName() : null;
        logger.info("Login name: " + loginName);
        return "home";
    }

    /**
     * Displays the home page for the PC builder.
     *
     * @return the builder template.
     */
    @RequestMapping("/configurator")
    public String pcBuilderHome() {
        return "configurator";
    }

    @GetMapping("/account")
    public String account(Principal principal) {
        final String loginName = principal != null ? principal.getName() : null;
        logger.info("Login name: " + loginName);
        return "account";
    }

}