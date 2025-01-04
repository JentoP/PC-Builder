package be.thomasmore.pcbuilder.controllers;

import be.thomasmore.pcbuilder.models.*;
import be.thomasmore.pcbuilder.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class PcBuildController {
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
    private PcBuildRepository pcBuildRepository;

    @RequestMapping("/builder")
    public String pcBuilderHome() {

        return "builder";
    }

    // Create or edit a PC build based on ID.
    @ModelAttribute("pcBuild")
    public PcBuild createPcBuild(@PathVariable(required = false) Integer id) {
        if (id == null) {
            return new PcBuild();
        }
        Optional<PcBuild> optionalPcBuild = pcBuildRepository.findById(id);
        if (optionalPcBuild.isPresent()) {
            return optionalPcBuild.get();
        } else {
            return null;
        }
    }

    // Add or edit a PC build, depending on the presence of an ID
    @GetMapping({"/addbuild", "/editbuild/{id}"})
    public String addOrEditPcBuild(@PathVariable(required = false) Integer id, Model model) {
        PcBuild pcBuild = createPcBuild(id);  // Fetch the build or create a new one
        model.addAttribute("pcBuild", pcBuild);

        Iterable<CPU> allCPUs = processors.findAll();
        Iterable<MOBO> allMOBOS = motherboards.findAll();
        Iterable<CHASSIS> allCases = cases.findAll();
        Iterable<COOLER> allCoolers = coolers.findAll();
        Iterable<GPU> allGPUs = graphicCards.findAll();
        Iterable<RAM> allMemory = memoryKits.findAll();
        Iterable<PSU> allPowerSupplies = powerSupplies.findAll();
        Iterable<DATA> allStorage = storage.findAll();

        model.addAttribute("allCases", allCases);
        model.addAttribute("allCoolers", allCoolers);
        model.addAttribute("allGPUs", allGPUs);
        model.addAttribute("allMemory", allMemory);
        model.addAttribute("allPowerSupplies", allPowerSupplies);
        model.addAttribute("allStorage", allStorage);
        model.addAttribute("allProcessors", allCPUs);
        model.addAttribute("allMotherboards", allMOBOS);
        System.out.println("Selected Cooling: " + pcBuild.getSelectedCooler());  // Log selected cooling

        // Add a flag to distinguish between "edit" and "add" modes in the template
        model.addAttribute("isEditMode", id != null);

        return "managebuild";  // Use the same template for both add and edit operations.
    }

    // Handle the POST request to add or edit a PC build
    @PostMapping({"/addbuild", "/editbuild/{id}"})
    public String addOrEditPcBuildPost(@ModelAttribute PcBuild pcBuild, Model model, RedirectAttributes redirectAttributes) {

        // Validate compatibility between selected CPU and MOBO
        if (!isCompatible(pcBuild)) {
//            Flash attributes are used to pass the errorMessage to the view on redirect.
            redirectAttributes.addFlashAttribute("errorMessage", "De geselecteerde componenten zijn niet compatibel met elkaar. Probeer opnieuw.");
            if (pcBuild.getBuildId() != null) {
                return "redirect:/editbuild/" + pcBuild.getBuildId();
            }
            return "redirect:/addbuild";
        }

        // Save the new or updated PC build
        pcBuildRepository.save(pcBuild);
        redirectAttributes.addFlashAttribute("successMessage", "Uw build werd opgeslagen!");
        return "redirect:/pcbuilds"; // Redirect to the list of PC builds
    }

    // Handle the request to delete a PC build
    @GetMapping("/deletebuild/{id}")
    public String deletePcBuild(@PathVariable Integer id) {
        Optional<PcBuild> pcBuild = pcBuildRepository.findById(id);
        if (pcBuild.isPresent()) {
            pcBuildRepository.deleteById(id);
        }
        return "redirect:/pcbuilds";
    }

    // Check if the selected components are compatible
    private boolean isCompatible(PcBuild pcBuild) {
        CPU selectedCPU = pcBuild.getSelectedCPU();
        MOBO selectedMOBO = pcBuild.getSelectedMOBO();
        RAM selectedMemory = pcBuild.getSelectedMemory();
        GPU selectedGPU = pcBuild.getSelectedGPU();
        PSU selectedPowerSupply = pcBuild.getSelectedPowerSupply();
        CHASSIS selectedCase = pcBuild.getSelectedCase();
        DATA selectedStorage = pcBuild.getSelectedStorage();
        COOLER selectedCooler = pcBuild.getSelectedCooler();

        // Check CPU and MOBO compatibility
        if (selectedCPU != null && selectedMOBO != null) {
            if (!selectedCPU.getSocketType().equals(selectedMOBO.getSocketType())) {
                return false;
            }
        }
        // Check MOBO and Memory compatibility
        if (selectedMOBO != null && selectedMemory != null) {
            if (!selectedMOBO.getMemoryType().equals(selectedMemory.getMemoryType())) {
                return false;
            }
        }
        // Check GPU power requirement and Power Supply capacity
        if (selectedGPU != null && selectedPowerSupply != null) {
            if (selectedGPU.getWattageUsage() > selectedPowerSupply.getWattageCapacity()) {
                return false;
            }
        }
        // Check MOBO and Case compatibility (form factor)
        if (selectedMOBO != null && selectedCase != null) {
            if (!selectedCase.getMoboFormFactor().equals(selectedMOBO.getMoboFormFactor())) {
                return false;
            }
        }
        // Check Power Supply and Case compatibility (form factor)
        if (selectedPowerSupply != null && selectedCase != null) {
            if (!selectedCase.getPsuFormFactor().equals(selectedPowerSupply.getPsuFormFactor())) {
                return false;
            }
        }
//        // Check Cooling and CPU compatibility (fan slots, radiator size)
//        if (selectedCooler != null && selectedCPU != null) {
//            if (!selectedCPU.hasCompatibleCoolerSupport(selectedCooler)) {
//                return false;
//            }
//        }
//
//        // Check Graphics Card and Case compatibility (GPU length, PCIe slots)
//        if (selectedGPU != null && selectedCase != null) {
//            if (!selectedCase.canFitGPU(selectedGPU)) {
//                return false;
//            }
//        }

//        // Check Power Supply wattage against the entire build
//        if (selectedPowerSupply != null) {
//            int totalWattageRequired = calculateTotalWattage(pcBuild);
//            if (selectedPowerSupply.getWattageCapacity() < totalWattageRequired) {
//                return false;
//            }
//        }

        return true;
    }

    // Helper method to calculate total wattage
    private int calculateTotalWattage(PcBuild pcBuild) {
        int totalWattage = 0;

        if (pcBuild.getSelectedCPU() != null) {
            totalWattage += pcBuild.getSelectedCPU().getWattageUsage();
        }
        if (pcBuild.getSelectedGPU() != null) {
            totalWattage += pcBuild.getSelectedGPU().getWattageUsage();
        }
        if (pcBuild.getSelectedStorage() != null) {
            totalWattage += pcBuild.getSelectedStorage().getWattageUsage();
        }
//        if (pcBuild.getSelectedCooler() != null) {
//            totalWattage += pcBuild.getSelectedCooler().getWattage();
//        }
        return totalWattage;
    }

    // View specific PC build details
    @GetMapping("/viewbuild/{id}")
    public String viewPcBuild(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<PcBuild> optionalPcBuild = pcBuildRepository.findById(id);
        if (optionalPcBuild.isPresent()) {
            PcBuild pcBuild = optionalPcBuild.get();
            model.addAttribute("pcBuild", pcBuild);
            return "viewbuild"; // Show build details

        }
        redirectAttributes.addFlashAttribute("errorMessage", "PC build not found.");
        return "redirect:/pcbuilds";
    }

    // List all PC builds for the user
    @GetMapping("/pcbuilds")
    public String viewPcBuilds(Model model) {
        Iterable<PcBuild> pcBuilds = pcBuildRepository.findAll();
        model.addAttribute("pcBuilds", pcBuilds);
        return "pcbuilds"; // Display all PC builds
    }
}