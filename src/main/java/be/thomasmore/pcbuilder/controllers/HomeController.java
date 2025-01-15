package be.thomasmore.pcbuilder.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

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