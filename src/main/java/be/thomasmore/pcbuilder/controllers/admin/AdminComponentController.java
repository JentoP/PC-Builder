package be.thomasmore.pcbuilder.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminComponentController {

    @RequestMapping("/components")
    public String adminComponents() {
        return "admin/components";
    }
}
