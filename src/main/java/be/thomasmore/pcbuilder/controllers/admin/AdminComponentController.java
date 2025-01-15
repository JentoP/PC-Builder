package be.thomasmore.pcbuilder.controllers.admin;

import be.thomasmore.pcbuilder.models.*;
import be.thomasmore.pcbuilder.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//    Wat gebeurt er?
//            • Spring roept eerst de @ModelAttribute method op die het gevraagde object
//    uit de database gaat halen
//• Dan kijkt Spring welke input fields geassocieerd zijn met dit object en vult die in in dit object. Hiervoor gebruikt Spring de attributen th:object en th:field.
//• De parameter party die we binnenkrijgen in de POST request handler is dit object:
//    dus de waardes uit de database, overschreven met de waardes uit de input fields.
//• We vragen de CrudRepository om dit object te saven in de database
//• Het object bevat het correcte id, en daardoor weet de CrudRepository welk update
//    statement hij moet sturen naar de database
    @ModelAttribute
    public Object findComponent(@PathVariable(required = false) String type, @PathVariable(required = false) Integer id) {
        if (id == null) {
            switch (type) {
                case "processor":
                    return new CPU();
                case "motherboard":
                    return new MOBO();
                case "memory":
                    return new RAM();
                case "storage":
                    return new DATA();
                case "graphiccard":
                    return new GPU();
                case "case":
                    return new CHASSIS();
                case "cooler":
                    return new COOLER();
                case "powersupply":
                    return new PSU();
                default:
                    return null;
            }
        } else {
            switch (type) {
                case "processor":
                    return processors.findById(id).orElse(null);
                case "motherboard":
                    return motherboards.findById(id).orElse(null);
                case "memory":
                    return memoryKits.findById(id).orElse(null);
                case "storage":
                    return storage.findById(id).orElse(null);
                case "graphiccard":
                    return graphicCards.findById(id).orElse(null);
                case "case":
                    return cases.findById(id).orElse(null);
                case "cooler":
                    return coolers.findById(id).orElse(null);
                case "powersupply":
                    return powerSupplies.findById(id).orElse(null);
                default:
                    return null;
            }
        }
    }

    @GetMapping("/addcomponent/{type}")
    public String addComponent(@PathVariable String type, Model model) {
        Object component = findComponent(type, null);
        model.addAttribute("component", component);
        model.addAttribute("type", type);
        return "admin/addcomponent";
    }

    @GetMapping("/editcomponent/{type}/{id}")
    public String editComponent(@PathVariable String type, @PathVariable Integer id, Model model) {
        Object component = findComponent(type, id);
        model.addAttribute("component", component);
        model.addAttribute("type", type);
        return "admin/editcomponent";
    }

    @PostMapping({"/editcomponent/{type}/{id}", "/addcomponent/{type}"})
    public String saveComponent(@PathVariable String type, @PathVariable(required = false) Integer id, @ModelAttribute Object component) {
//        checks if the id variable is null, and if so, sets the isAddAction boolean to true, indicating that the action is an "add" operation rather than an "edit" operation.
        boolean isAddAction = (id == null);

        switch (type) {
            case "processor":
                if (isAddAction) {
                    CPU newCPU = processors.save((CPU) component);
                    return "redirect:/componentdetails/" + newCPU.getId();
                } else {
                    processors.save((CPU) component);
                }
                break;
            case "motherboard":
                if (isAddAction) {
                    MOBO newMOBO = motherboards.save((MOBO) component);
                    return "redirect:/componentdetails/" + newMOBO.getId();
                } else {
                    motherboards.save((MOBO) component);
                }
                break;
            case "memory":
                if (isAddAction) {
                    RAM newRAM = memoryKits.save((RAM) component);
                    return "redirect:/componentdetails/" + newRAM.getId();
                } else {
                    memoryKits.save((RAM) component);
                }
                break;
            case "storage":
                if (isAddAction) {
                    DATA newDATA = storage.save((DATA) component);
                    return "redirect:/componentdetails/" + newDATA.getId();
                } else {
                    storage.save((DATA) component);
                }
                break;
            case "graphiccard":
                if (isAddAction) {
                    GPU newGPU = graphicCards.save((GPU) component);
                    return "redirect:/componentdetails/" + newGPU.getId();
                } else {
                    graphicCards.save((GPU) component);
                }
                break;
            case "case":
                if (isAddAction) {
                    CHASSIS newCHASSIS = cases.save((CHASSIS) component);
                    return "redirect:/componentdetails/" + newCHASSIS.getId();
                } else {
                    cases.save((CHASSIS) component);
                }
                break;
            case "cooler":
                if (isAddAction) {
                    COOLER newCOOLER = coolers.save((COOLER) component);
                    return "redirect:/componentdetails/" + newCOOLER.getId();
                } else {
                    coolers.save((COOLER) component);
                }
                break;
            case "powersupply":
                if (isAddAction) {
                    PSU newPSU = powerSupplies.save((PSU) component);
                    return "redirect:/componentdetails/" + newPSU.getId();
                } else {
                    powerSupplies.save((PSU) component);
                }
                break;
        }
        return "redirect:/componentdetails/" + id;
    }
}

