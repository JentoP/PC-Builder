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
        Iterable<CHASSIS> caseFromDb = cases.findAll();
        model.addAttribute("allCases", caseFromDb);
        Iterable<COOLING> coolingFromDb = coolingSolutions.findAll();
        model.addAttribute("allCooling", coolingFromDb);
        Iterable<GPU> graphicCardFromDb = graphicCards.findAll();
        model.addAttribute("allGraphicCards", graphicCardFromDb);
        Iterable<RAM> memoryFromDb = memoryKits.findAll();
        model.addAttribute("allMemory", memoryFromDb);
        Iterable<PSU> powerFromDb = powerSupplies.findAll();
        model.addAttribute("allPowerSupplies", powerFromDb);
        Iterable<DATA> storageFromDb = storage.findAll();
        model.addAttribute("allStorage", storageFromDb);

        return "componentlist";
    }

    @GetMapping("/processors/{id}")
    public String processors(@PathVariable Integer id, Model model) {
        Optional<CPU> cpuFromDb = processors.findById(id);
        long count = processors.count();
        if (cpuFromDb.isPresent()) {
            model.addAttribute("cpu", cpuFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);

        }
        return "processors";
    }

    @GetMapping("/motherboards/{id}")
    public String motherboards(@PathVariable Integer id, Model model) {
        Optional<MOBO> moboFromDb = motherboards.findById(id);
        long count = motherboards.count();
        if (moboFromDb.isPresent()) {
            model.addAttribute("mobo", moboFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "motherboards";
    }

    @GetMapping("/cases/{id}")
    public String cases(@PathVariable Integer id, Model model) {
        Optional<CHASSIS> caseFromDb = cases.findById(id);
        long count = cases.count();
        if (caseFromDb.isPresent()) {
            model.addAttribute("cases", caseFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "cases";
    }

    @GetMapping("/cooling/{id}")
    public String cooling(@PathVariable Integer id, Model model) {
        Optional<COOLING> coolingFromDb = coolingSolutions.findById(id);
        long count = coolingSolutions.count();
        if (coolingFromDb.isPresent()) {
            model.addAttribute("cooling", coolingFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "cooling";
    }

    @GetMapping("/graphiccards/{id}")
    public String graphicCards(@PathVariable Integer id, Model model) {
        Optional<GPU> graphicCardFromDb = graphicCards.findById(id);
        long count = graphicCards.count();
        if (graphicCardFromDb.isPresent()) {
            model.addAttribute("gpu", graphicCardFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "graphiccards";
    }

    @GetMapping("/memory/{id}")
    public String memory(@PathVariable Integer id, Model model) {
        Optional<RAM> memoryKitsFromDb = memoryKits.findById(id);
        long count = memoryKits.count();
        if (memoryKitsFromDb.isPresent()) {
            model.addAttribute("memory", memoryKitsFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "memory";
    }

    @GetMapping("/powersupplies/{id}")
    public String powerSupplies(@PathVariable Integer id, Model model) {
        Optional<PSU> powerSuppliesFromDb = powerSupplies.findById(id);
        long count = powerSupplies.count();
        if (powerSuppliesFromDb.isPresent()) {
            model.addAttribute("psu", powerSuppliesFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "powersupplies";
    }

    @GetMapping("/storage/{id}")
    public String storage(@PathVariable Integer id, Model model) {
        Optional<DATA> storageFromDb = storage.findById(id);
        long count = storage.count();
        if (storageFromDb.isPresent()) {
            model.addAttribute("data", storageFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "storage";
    }
}
