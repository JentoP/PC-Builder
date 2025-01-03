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

    private static final double DEFAULT_MIN_PRICE = 0.0;
    private static final double DEFAULT_MAX_PRICE = Double.MAX_VALUE;

    @GetMapping({"/components"})
    public String components(Model model) {

        return "components";
    }


    @GetMapping({"/lists/processors"})
    public String processorList(Model model,
                                @RequestParam(required = false) String searchWord,
                                @RequestParam(required = false) Double filterMinPrice,
                                @RequestParam(required = false) Double filterMaxPrice,
                                @RequestParam(required = false) String filterManufacturer,
                                @RequestParam(required = false) String filterSocket,
                                @RequestParam(required = false) Integer filterCore,
                                @RequestParam(required = false) String filterArchitecture,
                                @RequestParam(required = false) String filterCpuModel,
                                @RequestParam(required = false) Double filterClockSpeed,
                                @RequestParam(required = false) Double filterWattage) {
//      declare list
        List<CPU> filteredProcessors;

//      check first for  search
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredProcessors = processors.findBySearch(searchWord);

//      check for price filter
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredProcessors = processors.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );

//      if no filters are applied give all results
        } else if (filterManufacturer == null && filterSocket == null && filterCore == null && filterArchitecture == null && filterCpuModel == null && filterClockSpeed == null && filterWattage == null) {
            filteredProcessors = (List<CPU>) processors.findAll();

//      if filters are applied give individual filtered results
        } else filteredProcessors = processors.findByFilter(
                filterManufacturer == null ? "" : filterManufacturer,
                filterSocket == null ? "" : filterSocket,
                filterCore == null ? 0 : filterCore,
                filterArchitecture == null ? "" : filterArchitecture,
                filterCpuModel == null ? "" : filterCpuModel,
                filterClockSpeed == null ? 0.0 : filterClockSpeed,
                filterWattage == null ? 0.0 : filterWattage
        );


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
//      declare list
        List<MOBO> filteredMotherboards;

//      check first for search
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredMotherboards = motherboards.findBySearch(searchWord);

//      check for price filter
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredMotherboards = motherboards.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );

//      if no filters are applied give all results
        } else if (filterManufacturer == null && filterSocket == null && filterChipset == null && filterMemory == null && filterMoboFormFactor == null) {
            filteredMotherboards = (List<MOBO>) motherboards.findAll();

//      if filters are applied give individual filtered results
        } else {
            filteredMotherboards = motherboards.findByFilter(
                    filterManufacturer == null ? "" : filterManufacturer,
                    filterSocket == null ? "" : filterSocket,
                    filterChipset == null ? "" : filterChipset,
                    filterMemory == null ? "" : filterMemory,
                    filterMoboFormFactor == null ? "" : filterMoboFormFactor
            );
        }

        model.addAttribute("allMotherboards", motherboards.findAll());
        model.addAttribute("filteredMotherboards", filteredMotherboards);

        return "/lists/motherboards";
    }


    @GetMapping({"/lists/memory"})
    public String memoryList(Model model,
                             @RequestParam(required = false) String searchWord,
                             @RequestParam(required = false) Double filterMinPrice,
                             @RequestParam(required = false) Double filterMaxPrice,
                             @RequestParam(required = false) String filterManufacturer,
                             @RequestParam(required = false) Integer filterMemoryCapacity,
                             @RequestParam(required = false) String filterMemoryType,
                             @RequestParam(required = false) Integer filterClockSpeed) {
//      declare list
        List<RAM> filteredMemory;

//      check first for search
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredMemory = memoryKits.findBySearch(searchWord);

//      check for price filter
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredMemory = memoryKits.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );

//      if no filters are applied give all results
        } else if (filterManufacturer == null && filterMemoryCapacity == 0 && filterMemoryType == null && filterClockSpeed == 0) {
            filteredMemory = (List<RAM>) memoryKits.findAll();

//      if filters are applied give individual filtered results
        } else {
            filteredMemory = memoryKits.findByFilter(
                    filterManufacturer == null ? "" : filterManufacturer,
                    filterMemoryCapacity == null ? 0 : filterMemoryCapacity,
                    filterMemoryType == null ? "" : filterMemoryType,
                    filterClockSpeed == null ? 0 : filterClockSpeed
            );
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
                                  @RequestParam(required = false) Integer filterMemoryCapacity,
                                  @RequestParam(required = false) String filterInterfaceType) {
//      declare list
        List<GPU> filteredGraphicCards;

//      check first for search
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredGraphicCards = graphicCards.findBySearch(searchWord);

//      check for price filter
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredGraphicCards = graphicCards.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );

//      if no filters are applied give all results
        } else if (filterManufacturer == null && filterChipset == null && filterMemoryCapacity == 0 && filterInterfaceType == null) {
            filteredGraphicCards = (List<GPU>) graphicCards.findAll();

//      if filters are applied give individual filtered results
        } else {
            filteredGraphicCards = graphicCards.findByFilter(
                    filterManufacturer == null ? "" : filterManufacturer,
                    filterChipset == null ? "" : filterChipset,
                    filterMemoryCapacity == null ? 0 : filterMemoryCapacity,
                    filterInterfaceType == null ? "" : filterInterfaceType
            );
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
                              @RequestParam(required = false) Integer filterCapacity) {
//      declare list
        List<DATA> filteredStorage;

//      check first for search
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredStorage = storage.findBySearch(searchWord);

//      check for price filter
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredStorage = storage.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );

//      if no filters are applied give all results
        } else if (filterManufacturer == null && filterInterfaceType == null && filterStorageType == null && filterCapacity == null) {
            filteredStorage = (List<DATA>) storage.findAll();

//      if filters are applied give individual filtered results
        } else {
            filteredStorage = storage.findByFilter(
                    filterManufacturer == null ? "" : filterManufacturer,
                    filterInterfaceType == null ? "" : filterInterfaceType,
                    filterStorageType == null ? "" : filterStorageType,
                    filterCapacity == null ? 0 : filterCapacity
            );
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
                              @RequestParam(required = false) Integer filterFanSize,
                              @RequestParam(required = false) Integer filterRadiatorSize) {
//      declare list
        List<COOLING> filteredCooling;

//      check first for search
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredCooling = coolingSolutions.findBySearch(searchWord);

//      check for price filter
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredCooling = coolingSolutions.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );

//      if no filters are applied give all results
        } else if (filterManufacturer == null && filterSocketType == null && filterFanSize == null && filterRadiatorSize == null) {
            filteredCooling = (List<COOLING>) coolingSolutions.findAll();

//      if filters are applied give individual filtered results
        } else {
            filteredCooling = coolingSolutions.findByFilter(
                    filterManufacturer == null ? "" : filterManufacturer,
                    filterSocketType == null ? "" : filterSocketType,
                    filterFanSize == null ? 0 : filterFanSize,
                    filterRadiatorSize == null ? 0 : filterRadiatorSize
            );
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
                           @RequestParam(required = false) String filterPsuFormFactor) {
//      declare list
        List<CHASSIS> filteredCases;

//      check first for search
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredCases = cases.findBySearch(searchWord);

//      check for price filter
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredCases = cases.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );

//      if no filters are applied give all results
        } else if (filterManufacturer == null && filterMoboFormFactor == null && filterPsuFormFactor == null) {
            filteredCases = (List<CHASSIS>) cases.findAll();

//      if filters are applied give individual filtered results
        } else {
            filteredCases = cases.findByFilter(
                    filterManufacturer == null ? "" : filterManufacturer,
                    filterMoboFormFactor == null ? "" : filterMoboFormFactor,
                    filterPsuFormFactor == null ? "" : filterPsuFormFactor
            );
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

    @GetMapping({"/lists/powersupplies"})
    public String powerSuppliesList(Model model,
                                    @RequestParam(required = false) String searchWord,
                                    @RequestParam(required = false) Double filterMinPrice,
                                    @RequestParam(required = false) Double filterMaxPrice,
                                    @RequestParam(required = false) String filterManufacturer,
                                    @RequestParam(required = false) String filterWattage,
                                    @RequestParam(required = false) String filterEfficiency,
                                    @RequestParam(required = false) String filterPsuFormFactor) {

        List<PSU> filteredPowerSupplies;

        if (searchWord != null && !searchWord.isEmpty()) {
            filteredPowerSupplies = powerSupplies.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredPowerSupplies = powerSupplies.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else {
            filteredPowerSupplies = powerSupplies.findByFilter(
                    filterManufacturer,
                    filterWattage,
                    filterEfficiency,
                    filterPsuFormFactor);
        }

        model.addAttribute("allPowerSupplies", powerSupplies.findAll());
        model.addAttribute("filteredPowerSupplies", filteredPowerSupplies);

        return "/lists/powersupplies";
    }

    //methods for detail pages
    @GetMapping("/components/motherboard/{id}")
    public String motherboardDetails(@PathVariable Integer id, Model model) {
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
    public String chassisDetails(@PathVariable Integer id, Model model) {
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
    public String coolingDetails(@PathVariable Integer id, Model model) {
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
    public String graphicCardsDetails(@PathVariable Integer id, Model model) {
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
    public String memoryDetails(@PathVariable Integer id, Model model) {
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
    public String powerSuppliesDetails(@PathVariable Integer id, Model model) {
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
    public String storageDetails(@PathVariable Integer id, Model model) {
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
