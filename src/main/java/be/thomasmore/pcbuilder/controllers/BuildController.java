package be.thomasmore.pcbuilder.controllers;

import be.thomasmore.pcbuilder.models.*;
import be.thomasmore.pcbuilder.repos.BuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class BuildController {
    @Autowired
    private BuildRepository buildRepository;

    @GetMapping("/builder")
    public String builds(Model model) {
        Optional<CPU> cpuFromDb = buildRepository.findById(1);
        return "builder";
    }

}
