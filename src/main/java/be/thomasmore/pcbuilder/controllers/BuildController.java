package be.thomasmore.pcbuilder.controllers;

import be.thomasmore.pcbuilder.models.*;
import be.thomasmore.pcbuilder.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private ChassisRepository chassisRepository;
    @Autowired
    private ProcessorRepository processorRepository;

    @GetMapping("/builder")
    public String builds(Model model) {


        return "builder";
    }

    @GetMapping({"/components"})
    public String components(Model model) {

//        Iterating over all the repositories
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


//            Future use
//            List<MOBO> moboList = motherboards.findBySearch(searchWord);
//            List<CHASSIS> caseList = cases.findBySearch(searchWord);
//            List<COOLING> coolingList = coolingSolutions.findBySearch(searchWord);
//            List<GPU> gpuList = graphicCards.findBySearch(searchWord);
//            List<RAM> memoryList = memoryKits.findBySearch(searchWord);
//            List<PSU> powerList = powerSupplies.findBySearch(searchWord);
//            List<DATA> storageList = storage.findBySearch(searchWord);

        return "components";
    }


    @GetMapping({"/lists/processorlist"})
    public String processorList(Model model, @RequestParam(required = false) String searchWord) {
        List<CPU> cpuList = processorRepository.findBySearch(searchWord);
        model.addAttribute("cpuList", cpuList);
        return "/lists/processorlist";
    }

    @GetMapping("/components/processors/{id}")
    public String processors(@PathVariable Integer id, Model model) {
        Optional<CPU> cpuFromDb = processors.findById(id);
        long count = processors.count();
        if (cpuFromDb.isPresent()) {
            model.addAttribute("cpu", cpuFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);

        }
        return "components/processors";
    }

    @GetMapping("/components/motherboards/{id}")
    public String motherboards(@PathVariable Integer id, Model model) {
        Optional<MOBO> moboFromDb = motherboards.findById(id);
        long count = motherboards.count();
        if (moboFromDb.isPresent()) {
            model.addAttribute("mobo", moboFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "components/motherboards";
    }

    @GetMapping("/components/cases/{id}")
    public String cases(@PathVariable Integer id, Model model) {
        Optional<CHASSIS> caseFromDb = cases.findById(id);
        long count = cases.count();
        if (caseFromDb.isPresent()) {
            model.addAttribute("cases", caseFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "components/cases";
    }

    @GetMapping("/components/cooling/{id}")
    public String cooling(@PathVariable Integer id, Model model) {
        Optional<COOLING> coolingFromDb = coolingSolutions.findById(id);
        long count = coolingSolutions.count();
        if (coolingFromDb.isPresent()) {
            model.addAttribute("cooling", coolingFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "components/cooling";
    }

    @GetMapping("/components/graphiccards/{id}")
    public String graphicCards(@PathVariable Integer id, Model model) {
        Optional<GPU> graphicCardFromDb = graphicCards.findById(id);
        long count = graphicCards.count();
        if (graphicCardFromDb.isPresent()) {
            model.addAttribute("gpu", graphicCardFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "components/graphiccards";
    }

    @GetMapping("/components/memory/{id}")
    public String memory(@PathVariable Integer id, Model model) {
        Optional<RAM> memoryKitsFromDb = memoryKits.findById(id);
        long count = memoryKits.count();
        if (memoryKitsFromDb.isPresent()) {
            model.addAttribute("memory", memoryKitsFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "components/memory";
    }

    @GetMapping("/components/powersupplies/{id}")
    public String powerSupplies(@PathVariable Integer id, Model model) {
        Optional<PSU> powerSuppliesFromDb = powerSupplies.findById(id);
        long count = powerSupplies.count();
        if (powerSuppliesFromDb.isPresent()) {
            model.addAttribute("psu", powerSuppliesFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "components/powersupplies";
    }

    @GetMapping("/components/storage/{id}")
    public String storage(@PathVariable Integer id, Model model) {
        Optional<DATA> storageFromDb = storage.findById(id);
        long count = storage.count();
        if (storageFromDb.isPresent()) {
            model.addAttribute("data", storageFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "components/storage";
    }
}
