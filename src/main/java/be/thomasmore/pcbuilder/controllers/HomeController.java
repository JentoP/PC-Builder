package be.thomasmore.pcbuilder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/home"})
    public String Home(Model model) {
        return "home";
    }

    /**
     * Displays the home page for the PC builder.
     * @return the builder template.
     */
    @RequestMapping("/configurator")
    public String pcBuilderHome() {
        return "configurator";
    }
}