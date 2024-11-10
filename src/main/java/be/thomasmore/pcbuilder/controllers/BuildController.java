package be.thomasmore.pcbuilder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuildController {
    @GetMapping("/builder")
    public String builds(Model model) {
        return "builder";
    }

}
