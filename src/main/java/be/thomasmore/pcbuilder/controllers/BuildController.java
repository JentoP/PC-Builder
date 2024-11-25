package be.thomasmore.pcbuilder.controllers;

import be.thomasmore.pcbuilder.models.*;
import be.thomasmore.pcbuilder.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

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
    @Autowired
    private ProcessorRepository processorRepository;

    @GetMapping("/builder")
    public String builds(Model model) {
//        Iterating over all the repositories
//        Iterable<CPU> cpuFromDb = processors.findAll();
//        model.addAttribute("allProcessors", cpuFromDb);
//        Iterable<MOBO> moboFromDb = motherboards.findAll();
//        model.addAttribute("allMotherboards", moboFromDb);
//        Iterable<CHASSIS> caseFromDb = cases.findAll();
//        model.addAttribute("allCases", caseFromDb);
//        Iterable<COOLING> coolingFromDb = coolingSolutions.findAll();
//        model.addAttribute("allCooling", coolingFromDb);
//        Iterable<GPU> graphicCardFromDb = graphicCards.findAll();
//        model.addAttribute("allGraphicCards", graphicCardFromDb);
//        Iterable<RAM> memoryFromDb = memoryKits.findAll();
//        model.addAttribute("allMemory", memoryFromDb);
//        Iterable<PSU> powerFromDb = powerSupplies.findAll();
//        model.addAttribute("allPowerSupplies", powerFromDb);
//        Iterable<DATA> storageFromDb = storage.findAll();
//        model.addAttribute("allStorage", storageFromDb);

// without iterations
        model.addAttribute("allProcessors", processors.findAll());
        model.addAttribute("allMotherboards", motherboards.findAll());
        model.addAttribute("allCases", cases.findAll());
        model.addAttribute("allCooling", coolingSolutions.findAll());
        model.addAttribute("allGraphicCards", graphicCards.findAll());
        model.addAttribute("allMemory", memoryKits.findAll());
        model.addAttribute("allPowerSupplies", powerSupplies.findAll());
        model.addAttribute("allStorage", storage.findAll());

        return "builder";
    }
}
