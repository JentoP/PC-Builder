package be.thomasmore.pcbuilder.controllers;

import be.thomasmore.pcbuilder.models.*;
import be.thomasmore.pcbuilder.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PcBuildController {
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

    // View specific PC build details
    @GetMapping("/viewbuild/{id}")
    public String viewPcBuild(@PathVariable Integer id, Model model) {
        Optional<PcBuild> optionalPcBuild = pcBuildRepository.findById(id);
        if (optionalPcBuild.isPresent()) {
            PcBuild pcBuild = optionalPcBuild.get();
            model.addAttribute("pcBuild", pcBuild);
            return "viewbuild"; // Show build details
        } else {
            model.addAttribute("errorMessage", "PC Build not found.");
            return "error"; // Redirect to error page
        }
    }

    // Add or edit a PC build, depending on the presence of an ID
    @GetMapping({"/addbuild", "/editbuild/{id}"})
    public String addOrEditPcBuild(@PathVariable(required = false) Integer id, Model model) {
        PcBuild pcBuild = createPcBuild(id);  // Fetch the build or create a new one
        model.addAttribute("pcBuild", pcBuild);

        Iterable<CPU> allCPUs =  processors.findAll();
        Iterable<MOBO> allMOBOS =  motherboards.findAll();
        model.addAttribute("allProcessors", allCPUs);
        model.addAttribute("allMotherboards", allMOBOS);

        // Add a flag to distinguish between "edit" and "add" modes in the template
        model.addAttribute("isEditMode", id != null);

        return "managebuild";  // Use the same template for both add and edit operations.
    }

    // Handle the POST request to add or edit a PC build
    @PostMapping({"/addbuild", "/editbuild/{id}"})
    public String addOrEditPcBuildPost(@PathVariable(required = false) Integer id,
                                       PcBuild pcBuild,
                                       Model model,
                                       @RequestParam("selectedCPUId") Integer selectedCPUId) {
        Optional<CPU> selectedCPU = processors.findById(selectedCPUId);
        if (selectedCPU.isPresent()) {
            pcBuild.setSelectedCPU(selectedCPU.get());
        } else {
            model.addAttribute("errorMessage", "Invalid CPU selected");
            return "managebuild";
        }

        // Validate compatibility between selected CPU and MOBO
        if (!isCompatible(pcBuild.getSelectedCPU(), pcBuild.getSelectedMOBO())) {
            model.addAttribute("errorMessage", "Selected CPU and motherboard are not compatible.");
            return "managebuild";
        }

        // Save the new or updated PC build
        pcBuildRepository.save(pcBuild);

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

    // Check if the selected CPU and motherboard are compatible
    private boolean isCompatible(CPU selectedCPU, MOBO selectedMOBO) {
        if (selectedCPU == null || selectedMOBO == null) {
            return false;
        }
        return selectedCPU.getSocketType().equals(selectedMOBO.getSocketType());
    }

    // List all PC builds for the user
    @GetMapping("/pcbuilds")
    public String viewPcBuilds(Model model) {
        List<PcBuild> pcBuilds = (List<PcBuild>) pcBuildRepository.findAll();
        model.addAttribute("pcBuilds", pcBuilds);
        return "pcbuilds"; // Display all PC builds
    }
}