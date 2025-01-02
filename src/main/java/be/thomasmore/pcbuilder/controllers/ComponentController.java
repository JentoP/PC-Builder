package be.thomasmore.pcbuilder.controllers;

import be.thomasmore.pcbuilder.models.*;
import be.thomasmore.pcbuilder.repos.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class ComponentController {
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

    private static final double DEFAULT_MIN_PRICE = 0.0;
    private static final double DEFAULT_MAX_PRICE = Double.MAX_VALUE;

    @GetMapping({"/components"})
    public String components(Model model) {

//            Future uses
//            List<MOBO> moboList = motherboards.findBySearch(searchWord);
//            List<CHASSIS> caseList = cases.findBySearch(searchWord);
//            List<COOLING> coolingList = coolingSolutions.findBySearch(searchWord);
//            List<GPU> gpuList = graphicCards.findBySearch(searchWord);
//            List<RAM> memoryList = memoryKits.findBySearch(searchWord);
//            List<PSU> powerList = powerSupplies.findBySearch(searchWord);
//            List<DATA> storageList = storage.findBySearch(searchWord);

        return "components";
    }


    @GetMapping({"/lists/processors"})
    public String processorList(Model model,
                                @RequestParam(required = false) String searchWord,
                                @RequestParam(required = false) Double filterMinPrice,
                                @RequestParam(required = false) Double filterMaxPrice,
                                @RequestParam(required = false) String filterManufacturer,
                                @RequestParam(required = false) String filterSocket,
                                @RequestParam(required = false) String filterCore,
                                @RequestParam(required = false) String filterArchitecture) {

        List<CPU> filteredProcessors;

        if (searchWord != null && !searchWord.isEmpty()) {
            filteredProcessors = processors.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredProcessors = processors.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else {
            filteredProcessors = processors.findByFilter(
                    filterManufacturer,
                    filterSocket,
                    filterCore,
                    filterArchitecture);
        }

        model.addAttribute("allProcessors", processors.findAll());
        model.addAttribute("filteredProcessors", filteredProcessors);

        return "/lists/processors";
    }


    @GetMapping({"/lists/motherboards"})
    public String motherboardList(Model model,
                                  @RequestParam(required = false) String searchWord,
                                  @RequestParam(required = false) Double filterMinPrice,
                                  @RequestParam(required = false) Double filterMaxPrice,
                                  @RequestParam(required = false) String filterManufacturer,
                                  @RequestParam(required = false) String filterSocket,
                                  @RequestParam(required = false) String filterChipset,
                                  @RequestParam(required = false) String filterMemory,
                                  @RequestParam(required = false) String filterMoboFormFactor) {

        List<MOBO> filteredMotherboard;

        if (searchWord != null && !searchWord.isEmpty()) {
            filteredMotherboard = motherboards.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredMotherboard = motherboards.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else {
            filteredMotherboard = motherboards.findByFilter(
                    filterManufacturer,
                    filterSocket,
                    filterChipset,
                    filterMemory,
                    filterMoboFormFactor);
        }

        model.addAttribute("allMotherboards", motherboards.findAll());
        model.addAttribute("filteredMotherboard", filteredMotherboard);

        return "/lists/motherboards";
    }
    @GetMapping({"/lists/memory"})
    public String memoryList(Model model,
                             @RequestParam(required = false) String searchWord,
                             @RequestParam(required = false) Double filterMinPrice,
                             @RequestParam(required = false) Double filterMaxPrice,
                             @RequestParam(required = false) String filterManufacturer,
                             @RequestParam(required = false) String filterMemoryCapacity,
                             @RequestParam(required = false) String filterMemoryType,
                             @RequestParam(required = false) String filterTimings) {

        List<RAM> filteredMemory;

        if (searchWord != null && !searchWord.isEmpty()) {
            filteredMemory = memoryKits.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredMemory = memoryKits.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else {
            filteredMemory = memoryKits.findByFilter(
                    filterManufacturer,
                    filterMemoryCapacity,
                    filterMemoryType,
                    filterTimings);
        }

        model.addAttribute("allMemory", memoryKits.findAll());
        model.addAttribute("filteredMemory", filteredMemory);

        return "/lists/memory";
    }
    @GetMapping({"/lists/graphiccards"})
    public String graphicCardList(Model model,
                                  @RequestParam(required = false) String searchWord,
                                  @RequestParam(required = false) Double filterMinPrice,
                                  @RequestParam(required = false) Double filterMaxPrice,
                                  @RequestParam(required = false) String filterManufacturer,
                                  @RequestParam(required = false) String filterChipset,
                                  @RequestParam(required = false) String filterMemoryCapacity,
                                  @RequestParam(required = false) String filterInterfaceType) {

        List<GPU> filteredGraphicCards;

        if (searchWord != null && !searchWord.isEmpty()) {
            filteredGraphicCards = graphicCards.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredGraphicCards = graphicCards.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else {
            filteredGraphicCards = graphicCards.findByFilter(
                    filterManufacturer,
                    filterChipset,
                    filterMemoryCapacity,
                    filterInterfaceType);
        }

        model.addAttribute("allGraphicCards", graphicCards.findAll());
        model.addAttribute("filteredGraphicCards", filteredGraphicCards);

        return "/lists/graphiccards";
    }
    @GetMapping({"/lists/storage"})
    public String storageList(Model model,
                              @RequestParam(required = false) String searchWord,
                              @RequestParam(required = false) Double filterMinPrice,
                              @RequestParam(required = false) Double filterMaxPrice,
                              @RequestParam(required = false) String filterManufacturer,
                              @RequestParam(required = false) String filterInterfaceType,
                              @RequestParam(required = false) String filterStorageType,
                              @RequestParam(required = false) String filterCapacity) {

        List<DATA> filteredStorage;

        if (searchWord != null && !searchWord.isEmpty()) {
            filteredStorage = storage.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredStorage = storage.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else {
            filteredStorage = storage.findByFilter(
                    filterManufacturer,
                    filterInterfaceType,
                    filterStorageType,
                    filterCapacity);
        }

        model.addAttribute("allStorage", storage.findAll());
        model.addAttribute("filteredStorage", filteredStorage);

        return "/lists/storage";
    }
    @GetMapping({"/lists/cooling"})
    public String coolingList(Model model,
                              @RequestParam(required = false) String searchWord,
                              @RequestParam(required = false) Double filterMinPrice,
                              @RequestParam(required = false) Double filterMaxPrice,
                              @RequestParam(required = false) String filterManufacturer,
                              @RequestParam(required = false) String filterSocketType,
                              @RequestParam(required = false) String filterFanSize,
                              @RequestParam(required = false) String filterRadiatorSize) {

        List<COOLING> filteredCooling;

        if (searchWord != null && !searchWord.isEmpty()) {
            filteredCooling = coolingSolutions.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredCooling = coolingSolutions.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else {
            filteredCooling = coolingSolutions.findByFilter(
                    filterManufacturer,
                    filterSocketType,
                    filterFanSize,
                    filterRadiatorSize);
        }

        model.addAttribute("allCooling", coolingSolutions.findAll());
        model.addAttribute("filteredCooling", filteredCooling);

        return "/lists/cooling";
    }
    @GetMapping({"/lists/cases"})
    public String caseList(Model model,
                           @RequestParam(required = false) String searchWord,
                           @RequestParam(required = false) Double filterMinPrice,
                           @RequestParam(required = false) Double filterMaxPrice,
                           @RequestParam(required = false) String filterManufacturer,
                           @RequestParam(required = false) String filterMoboFormFactor,
                           @RequestParam(required = false) String filterPsuFormFactor,
                           @RequestParam(required = false) Boolean filterSidePanel) {

        List<CHASSIS> filteredCases;

        if (searchWord != null && !searchWord.isEmpty()) {
            filteredCases = cases.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredCases = cases.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else {
            filteredCases = cases.findByFilter(
                    filterManufacturer,
                    filterMoboFormFactor,
                    filterPsuFormFactor,
                    filterSidePanel);
        }

        model.addAttribute("allCases", cases.findAll());
        model.addAttribute("filteredCases", filteredCases);

        return "/lists/cases";
    }

    @GetMapping("/components/processor/{id}")
    public String processors(@PathVariable Integer id, Model model) {
        Optional<CPU> cpuFromDb = processors.findById(id);
        long count = processors.count();
        if (cpuFromDb.isPresent()) {
            model.addAttribute("cpu", cpuFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);

        }
        return "/components/processordetails";
    }

    @GetMapping("/components/motherboard/{id}")
    public String motherboards(@PathVariable Integer id, Model model) {
        Optional<MOBO> moboFromDb = motherboards.findById(id);
        long count = motherboards.count();
        if (moboFromDb.isPresent()) {
            model.addAttribute("mobo", moboFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "components/motherboarddetails";
    }

    @GetMapping("/components/case/{id}")
    public String cases(@PathVariable Integer id, Model model) {
        Optional<CHASSIS> caseFromDb = cases.findById(id);
        long count = cases.count();
        if (caseFromDb.isPresent()) {
            model.addAttribute("cases", caseFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "components/casedetails";
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
        return "components/coolingdetails";
    }

    @GetMapping("/components/graphiccard/{id}")
    public String graphicCards(@PathVariable Integer id, Model model) {
        Optional<GPU> graphicCardFromDb = graphicCards.findById(id);
        long count = graphicCards.count();
        if (graphicCardFromDb.isPresent()) {
            model.addAttribute("gpu", graphicCardFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "components/gpudetails";
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
        return "components/memorydetails";
    }

    @GetMapping("/components/powersupply/{id}")
    public String powerSupplies(@PathVariable Integer id, Model model) {
        Optional<PSU> powerSuppliesFromDb = powerSupplies.findById(id);
        long count = powerSupplies.count();
        if (powerSuppliesFromDb.isPresent()) {
            model.addAttribute("psu", powerSuppliesFromDb.get());
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        return "components/psudetails";
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
        return "components/storagedetails";
    }
}
