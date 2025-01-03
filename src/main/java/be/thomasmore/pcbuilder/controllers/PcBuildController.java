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

    // Get the form to edit an existing PC build
    @GetMapping("/editbuild/{id}")
    public String editPcBuild(@PathVariable Integer id, Model model) {
        model.addAttribute("pcBuild", createPcBuild(id));
        List<CPU> allCPUs = (List<CPU>) processors.findAll();
        List<MOBO> allMOBOS = (List<MOBO>) motherboards.findAll();
        model.addAttribute("allCPUs", allCPUs);
        model.addAttribute("allMOBOS", allMOBOS);
        return "editbuild";
    }

    // Handle the POST request to save the edited PC build
    @PostMapping("/editbuild/{id}")
    public String editPcBuildPost(@PathVariable Integer id, PcBuild pcBuild, Model model, @RequestParam("selectedCPUId") Integer selectedCPUId) {
        Optional<CPU> selectedCPU = processors.findById(selectedCPUId);
        if (selectedCPU.isPresent()) {
            pcBuild.setSelectedCPU(selectedCPU.get());
        } else {
            model.addAttribute("errorMessage", "Invalid CPU selected");
            return "user/addbuild";
        }
        // Validate compatibility between selected CPU and MOBO
        if (!isCompatible(pcBuild.getSelectedCPU(), pcBuild.getSelectedMOBO())) {
            model.addAttribute("errorMessage", "Selected CPU and motherboard are not compatible.");
            return "editbuild";
        }

        pcBuildRepository.save(pcBuild);
        return "redirect:/pcbuilds";
    }

    // Display the form to add a new PC build
    @GetMapping("/addbuild")
    public String addPcBuild(Model model) {
        Iterable<CPU> allCPUs = processors.findAll();
        Iterable<MOBO> allMOBOS = motherboards.findAll();
        model.addAttribute("allProcessors", allCPUs);
        model.addAttribute("allMotherboards", allMOBOS);
        model.addAttribute("pcBuild", new PcBuild()); // Empty build object
        return "addbuild";
    }

    // Handle the POST request to add a new PC build
    @PostMapping("/addbuild")
    public String addPcBuildPost(Model model, PcBuild pcBuild) {
        // Validate compatibility between selected CPU and MOBO
        if (!isCompatible(pcBuild.getSelectedCPU(), pcBuild.getSelectedMOBO())) {
            model.addAttribute("errorMessage", "Selected CPU and motherboard are not compatible.");
            model.addAttribute("allProcessors", processors.findAll());
            model.addAttribute("allMotherboards", motherboards.findAll());
            return "addbuild";
        }

        PcBuild savedPcBuild = pcBuildRepository.save(pcBuild);
        return "redirect:/pcbuilds";
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