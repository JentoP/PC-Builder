package be.thomasmore.pcbuilder.controllers.admin;

import be.thomasmore.pcbuilder.controllers.ComponentController;
import be.thomasmore.pcbuilder.models.*;
import be.thomasmore.pcbuilder.repos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller for managing admin operations related to PC components.
 * Handles operations such as adding, editing, and removing components.
 */
@Controller
@RequestMapping("/admin")
public class AdminComponentController {

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
    @Autowired
    private PcBuildRepository pcBuilds;
    private final Logger logger = LoggerFactory.getLogger(AdminComponentController.class);

    //Models
    /**
     * Finds or initializes a CPU object based on the provided ID.
     *
     * @param id the ID of the CPU (optional)
     * @return a CPU object from the database or a new instance if the ID is null.
     *
     * explanation: This method retrieves a CPU by its ID. If no ID is provided, it creates a new CPU object.
     * This method is similar to the following methods
     */
    @ModelAttribute("processor")
    public CPU findProcessor(@PathVariable(required = false) Integer id) {
        if (id == null) {
            return new CPU();
        }
        return processors.findById(id).orElse(null);
    }

    @ModelAttribute("motherboard")
    public MOBO findMotherboard(@PathVariable(required = false) Integer id) {
        if (id == null) {
            return new MOBO();
        }
        return motherboards.findById(id).orElse(null);
    }

    @ModelAttribute("memory")
    public RAM findMemory(@PathVariable(required = false) Integer id) {
        if (id == null) {
            return new RAM();
        }
        return memoryKits.findById(id).orElse(null);
    }

    @ModelAttribute("storage")
    public DATA findStorage(@PathVariable(required = false) Integer id) {
        if (id == null) {
            return new DATA();
        }
        return storage.findById(id).orElse(null);
    }

    @ModelAttribute("graphiccard")
    public GPU findGraphicCard(@PathVariable(required = false) Integer id) {
        if (id == null) {
            return new GPU();
        }
        return graphicCards.findById(id).orElse(null);
    }

    @ModelAttribute("case")
    public CHASSIS findCase(@PathVariable(required = false) Integer id) {
        if (id == null) {
            return new CHASSIS();
        }
        return cases.findById(id).orElse(null);
    }

    @ModelAttribute("cooler")
    public COOLER findCooler(@PathVariable(required = false) Integer id) {
        if (id == null) {
            return new COOLER();
        }
        return coolers.findById(id).orElse(null);
    }

    @ModelAttribute("powersupply")
    public PSU findPowerSupply(@PathVariable(required = false) Integer id) {
        if (id == null) {
            return new PSU();
        }
        return powerSupplies.findById(id).orElse(null);
    }


    //Add view
    /**
     * Displays the form for adding a new component based on the specified type.
     *
     * @param type  the type of component (e.g., processor, motherboard, etc.)
     * @param model the model to hold attributes for the view
     * @return the name of the view template to display
     *
     * Non-coder explanation: This method determines the type of component to add, prepares a blank form, and shows it to the user.
     */
    @GetMapping("/addcomponent/{type}")
    public String addComponent(@PathVariable String type, Model model) {
        Object component;
        switch (type) {
            case "processor":
                component = new CPU();
                break;
            case "motherboard":
                component = new MOBO();
                break;
            case "memory":
                component = new RAM();
                break;
            case "storage":
                component = new DATA();
                break;
            case "graphiccard":
                component = new GPU();
                break;
            case "case":
                component = new CHASSIS();
                break;
            case "cooler":
                component = new COOLER();
                break;
            case "powersupply":
                component = new PSU();
                break;
            default:
                component = null;
                break;
        }
        model.addAttribute("component", component);
        model.addAttribute("type", type);
        return "admin/addcomponent";
    }

    //Edit view
    /**
     * Loads an existing component of the given type and ID for editing and populates the model with the component data.
     *
     * @param type the type of the component to edit
     * @param id the ID of the component to edit
     * @param model the model to populate with the component data
     * @return the name of the view for editing a component
     *
     * This method fetches the details of an existing component for updating. It ensures the right information is displayed in the editing form.
     */
    @GetMapping("/editcomponent/{type}/{id}")
    public String editComponent(@PathVariable String type, @PathVariable Integer id, Model model) {
        Object component;
        //            eerst kijkt die naar het type, die type is een String die word opgehaald uit de view
        switch (type) {
            case "processor":
                component = findProcessor(id);
                break;
            case "motherboard":
                component = findMotherboard(id);
                break;
            case "memory":
                component = findMemory(id);
                break;
            case "storage":
                component = findStorage(id);
                break;
            case "graphiccard":
                component = findGraphicCard(id);
                break;
            case "case":
                component = findCase(id);
                break;
            case "cooler":
                component = findCooler(id);
                break;
            case "powersupply":
                component = findPowerSupply(id);
                break;
            default:
                component = null;
                break;
        }
        model.addAttribute("component", component);
        model.addAttribute("type", type);
        return "admin/editcomponent";
    }

    //    delete view
    /**
     * Handles the deletion of a component and its associated PC builds based on the provided type and ID.
     *
     * @param type the type of the component to delete
     * @param id the ID of the component to delete
     * @param model the model to populate with data (if needed)
     * @return a redirect URL to the list of components of the given type
     *
     * This method removes a selected component from the database and also deletes any PC builds that use this component.
     */
//    verwijderd ook een pcbuild waar het te verwijderen component in bevindt
    @GetMapping("/removecomponent/{type}/{id}")
    public String removeComponent(@PathVariable String type, @PathVariable Integer id, Model model) {
        switch (type) {
//            haalt de pcbuilds op die het te verwijderen component bevatten
            case "processor":
                removeSavedBuildsByComponent(id, "CPU");
                processors.deleteById(id);
                break;
            case "motherboard":
                removeSavedBuildsByComponent(id, "MOBO");
                motherboards.deleteById(id);
                break;
            case "memory":
                removeSavedBuildsByComponent(id, "RAM");
                memoryKits.deleteById(id);
                break;
            case "storage":
                removeSavedBuildsByComponent(id, "DATA");
                storage.deleteById(id);
                break;
            case "graphiccard":
                removeSavedBuildsByComponent(id, "GPU");
                graphicCards.deleteById(id);
                break;
            case "case":
                removeSavedBuildsByComponent(id, "CHASSIS");
                cases.deleteById(id);
                break;
            case "cooler":
                removeSavedBuildsByComponent(id, "COOLER");
                coolers.deleteById(id);
                break;
            case "powersupply":
                removeSavedBuildsByComponent(id, "PSU");
                powerSupplies.deleteById(id);
                break;
            default:
                throw new IllegalArgumentException("Invalid component type: " + type);
        }

        return "redirect:/lists/" + type;
    }

    private void removeSavedBuildsByComponent(Integer componentId, String type) {
        List<PcBuild> builds;
//        type wordt opgehaald tijdens de methode removeComponent
        switch (type) {
            case "CPU":
                builds = pcBuilds.findBuildsByCPU(componentId);
                break;
            case "MOBO":
                builds = pcBuilds.findBuildsByMOBO(componentId);
                break;
            case "RAM":
                builds = pcBuilds.findBuildsByMemory(componentId);
                break;
            case "DATA":
                builds = pcBuilds.findBuildsByStorage(componentId);
                break;
            case "GPU":
                builds = pcBuilds.findBuildsByGPU(componentId);
                break;
            case "CHASSIS":
                builds = pcBuilds.findBuildsByCase(componentId);
                break;
            case "COOLER":
                builds = pcBuilds.findBuildsByCooler(componentId);
                break;
            case "PSU":
                builds = pcBuilds.findBuildsByPowerSupply(componentId);
                break;
            default:
                throw new IllegalArgumentException("Invalid component type: " + type);
        }
        for (PcBuild build : builds) {
            pcBuilds.delete(build);
        }
    }


    //add en update post methodes

    /**
     * Saves a new processor to the database and redirects to its details page.
     *
     * @param component the CPU object to save
     * @return a redirect URL to the details page of the saved processor
     *
     * This method saves a newly created processor in the system and takes you to the page showing its details.
     */
    @PostMapping("/addcomponent/processor")
    public String saveProcessor(@ModelAttribute("processor") CPU component) {
        CPU newCPU = processors.save(component);
        return "redirect:/components/processor/" + newCPU.getId();
    }

    @PostMapping("/editcomponent/processor/{id}")
    public String updateProcessor(@PathVariable Integer id, @ModelAttribute("processor") CPU component) {
        processors.save(component);
        return "redirect:/components/processor/" + id;
    }

    @PostMapping("/addcomponent/motherboard")
    public String saveMotherboard(@ModelAttribute("motherboard") MOBO component) {
        MOBO newMOBO = motherboards.save(component);
        return "redirect:/components/motherboard/" + newMOBO.getId();
    }

    @PostMapping("/editcomponent/motherboard/{id}")
    public String updateMotherboard(@PathVariable Integer id, @ModelAttribute("motherboard") MOBO component) {
        motherboards.save(component);
        return "redirect:/components/motherboard/" + id;
    }

    @PostMapping("/addcomponent/memory")
    public String saveMemory(@ModelAttribute("memory") RAM component) {
        RAM newRAM = memoryKits.save(component);
        return "redirect:/components/memory/" + newRAM.getId();
    }

    @PostMapping("/editcomponent/memory/{id}")
    public String updateMemory(@PathVariable Integer id, @ModelAttribute("memory") RAM component) {
        memoryKits.save(component);
        return "redirect:/components/memory/" + id;
    }

    @PostMapping("/addcomponent/storage")
    public String saveStorage(@ModelAttribute("storage") DATA component) {
        DATA newDATA = storage.save(component);
        return "redirect:/components/storage/" + newDATA.getId();
    }

    @PostMapping("/editcomponent/storage/{id}")
    public String updateStorage(@PathVariable Integer id, @ModelAttribute("storage") DATA component) {
        storage.save(component);
        return "redirect:/components/storage/" + id;
    }

    @PostMapping("/addcomponent/graphiccard")
    public String saveGraphicCard(@ModelAttribute("graphiccard") GPU component) {
        GPU newGPU = graphicCards.save(component);
        return "redirect:/components/graphiccard/" + newGPU.getId();
    }

    @PostMapping("/editcomponent/graphiccard/{id}")
    public String updateGraphicCard(@PathVariable Integer id, @ModelAttribute("graphiccard") GPU component) {
        graphicCards.save(component);
        return "redirect:/components/graphiccard/" + id;
    }

    @PostMapping("/addcomponent/case")
    public String saveCase(@ModelAttribute("case") CHASSIS component) {
        CHASSIS newCHASSIS = cases.save(component);
        return "redirect:/components/case/" + newCHASSIS.getId();
    }

    @PostMapping("/editcomponent/case/{id}")
    public String updateCase(@PathVariable Integer id, @ModelAttribute("case") CHASSIS component) {
        cases.save(component);
        return "redirect:/components/case/" + id;
    }

    @PostMapping("/addcomponent/cooler")
    public String saveCooler(@ModelAttribute("cooler") COOLER component) {
        COOLER newCOOLER = coolers.save(component);
        return "redirect:/components/cooler/" + newCOOLER.getId();
    }

    @PostMapping("/editcomponent/cooler/{id}")
    public String updateCooler(@PathVariable Integer id, @ModelAttribute("cooler") COOLER component) {
        coolers.save(component);
        return "redirect:/components/cooler/" + id;
    }

    @PostMapping("/addcomponent/powersupply")
    public String savePowerSupply(@ModelAttribute("powersupply") PSU component) {
        PSU newPSU = powerSupplies.save(component);
        return "redirect:/components/powersupply/" + newPSU.getId();
    }

    @PostMapping("/editcomponent/powersupply/{id}")
    public String updatePowerSupply(@PathVariable Integer id, @ModelAttribute("powersupply") PSU component) {
        powerSupplies.save(component);
        return "redirect:/components/powersupply/" + id;
    }

}


