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
    @Autowired
    private ProcessorRepository processorRepository;

    private static final double DEFAULT_MIN_PRICE = 0.0;
    private static final double DEFAULT_MAX_PRICE = Double.MAX_VALUE;

    @GetMapping({"/components"})
    public String components(Model model) {

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
    public String processorList(Model model,
                                @RequestParam(required = false) String filterManufacturer,
                                @RequestParam(required = false) String filterSocket,
                                @RequestParam(required = false) Double filterMinPrice,
                                @RequestParam(required = false) Double filterMaxPrice,
                                @RequestParam(required = false) String filterCore,
                                @RequestParam(required = false) String filterArchitecture,
                                @RequestParam(required = false) String searchWord) {

        Iterable<CPU> filteredProcessors = processors.findAll();
//first checks and applies filters based on the param
        if (filterManufacturer != null && !filterManufacturer.isEmpty()) {
            filteredProcessors = processors.findByManufacturer(filterManufacturer);
        }
        if (filterSocket != null && !filterSocket.isEmpty()) {
            filteredProcessors = processors.findBySocket(filterSocket);
        }
        if (filterCore != null && !filterCore.isEmpty()) {
            filteredProcessors = processors.findByCore(filterCore);
        }
        if (filterArchitecture != null && !filterArchitecture.isEmpty()) {
            filteredProcessors = processors.findByArchitecture(filterArchitecture);
        }
//        filters price range if one price has a param, defaults the other value if null
        if (filterMinPrice != null || filterMaxPrice != null) {
            filteredProcessors = processors.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        }
//        if user uses search then no filters are applied but the search is applied
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredProcessors = processors.findBySearch(searchWord);
        }

        model.addAttribute("filteredProcessors", filteredProcessors);

        return "/lists/processorlist";
    }


    @GetMapping({"/lists/motherboardlist"})
    public String motherboardList(Model model,
                                  @RequestParam(required = false) String filterManufacturer,
                                  @RequestParam(required = false) String filterSocket,
                                  @RequestParam(required = false) Double filterMinPrice,
                                  @RequestParam(required = false) Double filterMaxPrice,
                                  @RequestParam(required = false) String filterChipset,
                                  @RequestParam(required = false) String filterMemory,
                                  @RequestParam(required = false) String filterMoboFormFactor,
                                  @RequestParam(required = false) String searchWord) {

        Iterable<MOBO> filteredMotherboard = motherboards.findAll();

        //first checks and applies filters based on the param
        if (filterManufacturer != null && !filterManufacturer.isEmpty()) {
            filteredMotherboard = motherboards.findByManufacturer(filterManufacturer);
        }
        if (filterSocket != null && !filterSocket.isEmpty()) {
            filteredMotherboard = motherboards.findBySocket(filterSocket);
        }
        if (filterMemory != null && !filterMemory.isEmpty()) {
            filteredMotherboard = motherboards.findByMemoryType(filterMemory);
        }
        if (filterMoboFormFactor != null && !filterMoboFormFactor.isEmpty()) {
            filteredMotherboard = motherboards.findByMoboFormFactor(filterMoboFormFactor);
        }

        if (filterChipset != null && !filterChipset.isEmpty()) {
            filteredMotherboard = motherboards.findByChipset(filterChipset);
        }
//        filters price range if one price has a param, defaults the other value if null
        if (filterMinPrice != null || filterMaxPrice != null) {
            filteredMotherboard = motherboards.findByPrice(
                    filterMinPrice != null ? filterMinPrice : 0.0,
                    filterMaxPrice != null ? filterMaxPrice : Double.MAX_VALUE
            );
        }
//        if user uses search then no filters are applied but the search is applied
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredMotherboard = motherboards.findBySearch(searchWord);
        }

        model.addAttribute("filteredMotherboard", filteredMotherboard);

        return "/lists/motherboardlist";
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
