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
    private CoolerRepository coolers;
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

    /**
     * Displays the components page.
     *
     * @param model the model object to store attributes for the view.
     * @return the name of the Thymeleaf template for components.
     */
    @GetMapping({"/components"})
    public String components(Model model) {
        return "components";
    }

    /**
     * Lijst alle processors met optionele filters zoals prijsklasse, fabrikant, socket type, enz.
     * Filters worden toegepast op basis van de requestparameters, en het resultaat wordt doorgegeven aan de view.
     *
     * @param model      het modelobject om attributen voor de view op te slaan.
     * @param searchWord het woord om processors op naam te doorzoeken.
     * @return de naam van de Thymeleaf-template voor de processorlijst.
     */
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
        // Declareert een lijst voor de gefilterde processors
        List<CPU> filteredProcessors;

        // Controleert of er een zoekwoord is opgegeven
        if (searchWord != null && !searchWord.isEmpty()) {
            // Zoek processors op basis van het zoekwoord
            filteredProcessors = processors.findBySearch(searchWord);

            // Controleert of er een prijsfilter is opgegeven
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            // Zoek processors binnen het opgegeven prijsbereik
            filteredProcessors = processors.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );

            // Controleert of er geen filters zijn opgegeven
        } else if (filterManufacturer == null && filterSocket == null && filterCore == null && filterArchitecture == null && filterCpuModel == null && filterClockSpeed == null && filterWattage == null) {
            // Haal alle processors op als er geen filters zijn
            filteredProcessors = (List<CPU>) processors.findAll();

            // Als er filters zijn opgegeven, pas individuele filters toe
        } else {
            filteredProcessors = processors.findByFilter(
                    filterManufacturer == null ? "" : filterManufacturer,
                    filterSocket == null ? "" : filterSocket,
                    filterCore == null ? 0 : filterCore,
                    filterArchitecture == null ? "" : filterArchitecture,
                    filterCpuModel == null ? "" : filterCpuModel,
                    filterClockSpeed == null ? 0.0 : filterClockSpeed,
                    filterWattage == null ? 0.0 : filterWattage
            );
        }

        // Voeg alle processors toe aan het model voor gebruik in de template
        model.addAttribute("allProcessors", processors.findAll());
        // Voeg de gefilterde processors toe aan het model
        model.addAttribute("filteredProcessors", filteredProcessors);

        // Retourneer de naam van de Thymeleaf-template voor de processorlijst
        return "processors";
    }

    /**
     * Lijst alle motherboards met optionele filters zoals prijsklasse, fabrikant, socket type, enz.
     * Filters worden toegepast op basis van de requestparameters, en het resultaat wordt doorgegeven aan de view.
     *
     * @param model      het modelobject om attributen voor de view op te slaan.
     * @param searchWord het woord om processors op naam te doorzoeken.
     * @return
     */
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
        List<MOBO> filteredMotherboards;
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredMotherboards = motherboards.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredMotherboards = motherboards.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else if (filterManufacturer == null && filterSocket == null && filterChipset == null && filterMemory == null && filterMoboFormFactor == null) {
            filteredMotherboards = (List<MOBO>) motherboards.findAll();
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
        return "motherboards";
    }

    /**
     * Lijst alle memorykits met optionele filters zoals prijsklasse, fabrikant, socket type, enz.
     * Filters worden toegepast op basis van de requestparameters, en het resultaat wordt doorgegeven aan de view.
     *
     * @param model      het modelobject om attributen voor de view op te slaan.
     * @param searchWord het woord om processors op naam te doorzoeken.
     * @return
     */
    @GetMapping({"/lists/memorys"})
    public String memoryList(Model model,
                             @RequestParam(required = false) String searchWord,
                             @RequestParam(required = false) Double filterMinPrice,
                             @RequestParam(required = false) Double filterMaxPrice,
                             @RequestParam(required = false) String filterManufacturer,
                             @RequestParam(required = false) Integer filterMemoryCapacity,
                             @RequestParam(required = false) String filterMemoryType,
                             @RequestParam(required = false) Integer filterClockSpeed) {
        List<RAM> filteredMemory;

        if (searchWord != null && !searchWord.isEmpty()) {
            filteredMemory = memoryKits.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredMemory = memoryKits.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else if (filterManufacturer == null && filterMemoryCapacity == null && filterMemoryType == null && filterClockSpeed == null) {
            filteredMemory = (List<RAM>) memoryKits.findAll();
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
        return "memory";
    }

    /**
     * Lijst alle graphiccards met optionele filters zoals prijsklasse, fabrikant, socket type, enz.
     * Filters worden toegepast op basis van de requestparameters, en het resultaat wordt doorgegeven aan de view.
     *
     * @param model      het modelobject om attributen voor de view op te slaan.
     * @param searchWord het woord om processors op naam te doorzoeken.
     * @return
     */
    @GetMapping({"/lists/graphiccards"})
    public String graphicCardList(Model model,
                                  @RequestParam(required = false) String searchWord,
                                  @RequestParam(required = false) Double filterMinPrice,
                                  @RequestParam(required = false) Double filterMaxPrice,
                                  @RequestParam(required = false) String filterManufacturer,
                                  @RequestParam(required = false) String filterChipset,
                                  @RequestParam(required = false) Integer filterMemoryCapacity,
                                  @RequestParam(required = false) String filterInterfaceType) {
        List<GPU> filteredGraphicCards;
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredGraphicCards = graphicCards.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredGraphicCards = graphicCards.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else if (filterManufacturer == null && filterChipset == null && filterMemoryCapacity == null && filterInterfaceType == null) {
            filteredGraphicCards = (List<GPU>) graphicCards.findAll();
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
        return "graphiccards";
    }

    /**
     * Lijst alle storages met optionele filters zoals prijsklasse, fabrikant, socket type, enz.
     * Filters worden toegepast op basis van de requestparameters, en het resultaat wordt doorgegeven aan de view.
     *
     * @param model          het modelobject om attributen voor de view op te slaan.
     * @param searchWord     het woord om processors op naam te doorzoeken.
     * @param filterMinPrice de minimumprijs voor het filteren van processors.
     * @return
     */
    @GetMapping({"/lists/storages"})
    public String storageList(Model model,
                              @RequestParam(required = false) String searchWord,
                              @RequestParam(required = false) Double filterMinPrice,
                              @RequestParam(required = false) Double filterMaxPrice,
                              @RequestParam(required = false) String filterManufacturer,
                              @RequestParam(required = false) String filterInterfaceType,
                              @RequestParam(required = false) String filterStorageType,
                              @RequestParam(required = false) Integer filterCapacity) {
        List<DATA> filteredStorage;
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredStorage = storage.findBySearch(searchWord);

        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredStorage = storage.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );

        } else if (filterManufacturer == null && filterInterfaceType == null && filterStorageType == null && filterCapacity == null) {
            filteredStorage = (List<DATA>) storage.findAll();

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
        return "storage";
    }

    /**
     * Lijst alle coolers met optionele filters zoals prijsklasse, fabrikant, socket type, enz.
     * Filters worden toegepast op basis van de requestparameters, en het resultaat wordt doorgegeven aan de view.
     *
     * @param model      het modelobject om attributen voor de view op te slaan.
     * @param searchWord het woord om processors op naam te doorzoeken.
     * @return
     */
    @GetMapping({"/lists/coolers"})
    public String coolerList(Model model,
                             @RequestParam(required = false) String searchWord,
                             @RequestParam(required = false) Double filterMinPrice,
                             @RequestParam(required = false) Double filterMaxPrice,
                             @RequestParam(required = false) String filterManufacturer,
                             @RequestParam(required = false) String filterSocketType,
                             @RequestParam(required = false) Integer filterFanSize,
                             @RequestParam(required = false) Integer filterRadiatorSize) {
        List<COOLER> filteredCOOLER;
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredCOOLER = coolers.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredCOOLER = coolers.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else if (filterManufacturer == null && filterSocketType == null && filterFanSize == null && filterRadiatorSize == null) {
            filteredCOOLER = (List<COOLER>) coolers.findAll();
        } else {
            filteredCOOLER = coolers.findByFilter(
                    filterManufacturer == null ? "" : filterManufacturer,
                    filterSocketType == null ? "" : filterSocketType,
                    filterFanSize == null ? 0 : filterFanSize,
                    filterRadiatorSize == null ? 0 : filterRadiatorSize
            );
        }
        model.addAttribute("allCoolers", coolers.findAll());
        model.addAttribute("filteredCooler", filteredCOOLER);
        return "coolers";
    }

    /**
     * Lijst alle cases met optionele filters zoals prijsklasse, fabrikant, socket type, enz.
     * Filters worden toegepast op basis van de requestparameters, en het resultaat wordt doorgegeven aan de view.
     *
     * @param model      het modelobject om attributen voor de view op te slaan.
     * @param searchWord het woord om processors op naam te doorzoeken.
     * @return
     */

    @GetMapping({"/lists/cases"})
    public String caseList(Model model,
                           @RequestParam(required = false) String searchWord,
                           @RequestParam(required = false) Double filterMinPrice,
                           @RequestParam(required = false) Double filterMaxPrice,
                           @RequestParam(required = false) String filterManufacturer,
                           @RequestParam(required = false) String filterMoboFormFactor,
                           @RequestParam(required = false) String filterPsuFormFactor) {
        List<CHASSIS> filteredCases;
        if (searchWord != null && !searchWord.isEmpty()) {
            filteredCases = cases.findBySearch(searchWord);
        } else if (filterMinPrice != null || filterMaxPrice != null) {
            filteredCases = cases.findByPrice(
                    filterMinPrice != null ? filterMinPrice : DEFAULT_MIN_PRICE,
                    filterMaxPrice != null ? filterMaxPrice : DEFAULT_MAX_PRICE
            );
        } else if (filterManufacturer == null && filterMoboFormFactor == null && filterPsuFormFactor == null) {
            filteredCases = (List<CHASSIS>) cases.findAll();
        } else {
            filteredCases = cases.findByFilter(
                    filterManufacturer == null ? "" : filterManufacturer,
                    filterMoboFormFactor == null ? "" : filterMoboFormFactor,
                    filterPsuFormFactor == null ? "" : filterPsuFormFactor
            );
        }
        model.addAttribute("allCases", cases.findAll());
        model.addAttribute("filteredCases", filteredCases);
        return "cases";
    }

    /**
     * Lijst alle power supplies met optionele filters zoals prijsklasse, fabrikant, socket type, enz.
     * Filters worden toegepast op basis van de requestparameters, en het resultaat wordt doorgegeven aan de view.
     *
     * @param model      het modelobject om attributen voor de view op te slaan.
     * @param searchWord het woord om processors op naam te doorzoeken.
     * @return
     */
    @GetMapping({"/lists/powersupplys"})
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
        return "powersupplies";
    }

    /**
     * Haalt de details op van een specifiek component op basis van het type en de ID.
     * Het toont het vorige en volgende component, circulair.
     *
     * @param type  het type van het component (bijv. processor, moederbord, enz.)
     * @param id    het ID van het component
     * @param model het modelobject voor het doorgeven van gegevens aan de weergave
     * @return de naam van de Thymeleaf-template voor de detailpagina van het component
     */
    @GetMapping("/components/{type}/{id}")
    public String componentDetails(@PathVariable String type, @PathVariable Integer id, Model model) {
        // Haalt het totaal aantal componenten van een bepaald type op
        long count = getCount(type);
        // Vindt het specifieke component op basis van het type en ID uit de database
        Optional<?> componentFromDb = getComponentFromDb(type, id);

        if (componentFromDb.isPresent()) {
            // Voeg het gevonden component toe aan het model
            model.addAttribute("component", componentFromDb.get());
            // Voeg het ID van het vorige component toe (circulair: terug naar het laatste als ID 1 is)
            model.addAttribute("previousId", id > 1 ? id - 1 : count);
            // Voeg het ID van het volgende component toe (circulair: terug naar het eerste als het het laatste is)
            model.addAttribute("nextId", id < count ? id + 1 : 1);
        }
        // Voeg het type component toe aan het model om een dynamische detailpagina te genereren
        model.addAttribute("type", type);
        // Retourneer de naam van de Thymeleaf-template voor de detailpagina
        return "componentdetails";
    }

    /**
     * Retourneert het totaal aantal componenten op basis van het opgegeven type.
     *
     * @param type het type van het component (bijv. processor, moederbord, enz.)
     * @return het totaal aantal componenten van het opgegeven type
     * @throws IllegalArgumentException als het type onbekend is
     */
    private long getCount(String type) {
        // Retourneert het totaal aantal componenten op basis van het type
        switch (type) {
            case "processor":
                return processors.count();
            case "motherboard":
                return motherboards.count();
            case "case":
                return cases.count();
            case "cooler":
                return coolers.count();
            case "graphiccard":
                return graphicCards.count();
            case "memory":
                return memoryKits.count();
            case "powersupply":
                return powerSupplies.count();
            case "storage":
                return storage.count();
            default:
                // Gooi een uitzondering als het type onbekend is
                throw new IllegalArgumentException("Onbekend componenttype: " + type);
        }
    }

    /**
     * Zoekt een specifiek component op basis van type en ID in de database.
     *
     * @param type het type van het component (bijv. processor, moederbord, enz.)
     * @param id   het ID van het component
     * @return een Optional met het component als het wordt gevonden, anders Optional.empty()
     * @throws IllegalArgumentException als het type onbekend is
     */
    private Optional<?> getComponentFromDb(String type, Integer id) {
        // Zoekt een specifiek component op basis van type en ID in de database
        switch (type) {
            case "processor":
                return processors.findById(id);
            case "motherboard":
                return motherboards.findById(id);
            case "case":
                return cases.findById(id);
            case "cooler":
                return coolers.findById(id);
            case "graphiccard":
                return graphicCards.findById(id);
            case "memory":
                return memoryKits.findById(id);
            case "powersupply":
                return powerSupplies.findById(id);
            case "storage":
                return storage.findById(id);
            default:
                // Gooi een uitzondering als het type onbekend is
                throw new IllegalArgumentException("Onbekend componenttype: " + type);
        }
    }
}
