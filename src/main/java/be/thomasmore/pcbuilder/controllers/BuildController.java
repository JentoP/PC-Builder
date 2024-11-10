package be.thomasmore.pcbuilder.controllers;

import be.thomasmore.pcbuilder.models.*;
import be.thomasmore.pcbuilder.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
public class BuildController {
    @Autowired
    private ChassisRepository cases;
    @Autowired
    private CoolingRepository coolingSolutions;
    @Autowired
    private GraphicsRepository graphicCards;
    @Autowired
    private MemoryRepository memoryKits;
    @Autowired
    private MotherboardRepository motherboards;
    @Autowired
    private PowerRepository powerSupplies;
    @Autowired
    private ProcessorRepository processors;
    @Autowired
    private StorageRepository storage;

    @GetMapping("/builder")
    public String builds(Model model) {
        return "builder";
    }

    @GetMapping({"/components"})
    public String components(Model model) {
        Iterable<CPU> cpuFromDb = processors.findAll();
        model.addAttribute("allProcessors", cpuFromDb);
        Iterable<MOBO> moboFromDb = motherboards.findAll();
        model.addAttribute("allMotherboards", moboFromDb);
        return "components";
    }

    @GetMapping("/processors/{id}")
    public String getProcessor(@PathVariable Integer id, Model model) {
        Optional<CPU> cpuFromDb = processors.findById(id);
        if (cpuFromDb.isPresent()) {
            model.addAttribute("component", cpuFromDb.get());
        }
        return "processors";
    }

    @GetMapping("/component/motherboards/{id}")
    public String getMotherboard(@PathVariable Integer id, Model model) {
        Optional<MOBO> moboFromDb = motherboards.findById(id);
        model.addAttribute("component", moboFromDb);
        return "motherboards";
    }

    @GetMapping("/component/cases/{id}")
    public String getCase(@PathVariable Integer id, Model model) {
        Optional<CHASSIS> caseFromDb = cases.findById(id);
        model.addAttribute("component", caseFromDb);
        return "cases";
    }

    @GetMapping("/component/cooling/{id}")
    public String getCooling(@PathVariable Integer id, Model model) {
        Optional<COOLING> coolingFromDb = coolingSolutions.findById(id);
        model.addAttribute("component", coolingFromDb);
        return "cooling";
    }

    @GetMapping("/component/graphicCards/{id}")
    public String getGraphicCard(@PathVariable Integer id, Model model) {
        Optional<GPU> graphicCardFromDb = graphicCards.findById(id);
        model.addAttribute("component", graphicCardFromDb);
        return "graphicCards";
    }

    @GetMapping("/component/memoryKits/{id}")
    public String getMemoryKit(@PathVariable Integer id, Model model) {
        Optional<RAM> memoryKitsFromDb = memoryKits.findById(id);
        model.addAttribute("component", memoryKitsFromDb);
        return "memoryKits";
    }

    @GetMapping("/component/powerSupplies/{id}")
    public String getPowerSupply(@PathVariable Integer id, Model model) {
        Optional<PSU> powerSuppliesFromDb = powerSupplies.findById(id);
        model.addAttribute("component", powerSuppliesFromDb);
        return "powerSupply";
    }

    @GetMapping("/component/storage/{id}")
    public String getStorage(@PathVariable Integer id, Model model) {
        Optional<DATA> storageFromDb = storage.findById(id);
        model.addAttribute("component", storageFromDb);
        return "storage";
    }
}
