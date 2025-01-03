package be.thomasmore.pcbuilder.controllers;

import be.thomasmore.pcbuilder.models.*;
import be.thomasmore.pcbuilder.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
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

    // Get the form to edit an existing PC build
    @GetMapping("/editbuild/{id}")
    public String editPcBuild(@PathVariable Integer id, Model model) {
        model.addAttribute("pcBuild", createPcBuild(id));
        List<CPU> allCPUs = (List<CPU>) processors.findAll();
        List<MOBO> allMOBOS = (List<MOBO>) motherboards.findAll();
        model.addAttribute("allCPUs", allCPUs);
        model.addAttribute("allMOBOS", allMOBOS);
        return "user/editbuild";
    }

    // Handle the POST request to save the edited PC build
    @PostMapping("/editbuild/{id}")
    public String editPcBuildPost(@PathVariable Integer id, PcBuild pcBuild, Model model) {
        // Validate compatibility between selected CPU and MOBO
        if (!isCompatible(pcBuild.getSelectedCPU(), pcBuild.getSelectedMOBO())) {
            model.addAttribute("errorMessage", "Selected CPU and motherboard are not compatible.");
            return "user/editbuild";
        }

        pcBuildRepository.save(pcBuild);
        return "redirect:/user/pcbuilds";
    }

    // Display the form to add a new PC build
    @GetMapping("/addbuild")
    public String addPcBuild(Model model) {
        List<CPU> allCPUs = (List<CPU>) processors.findAll();
        List<MOBO> allMOBOS = (List<MOBO>) motherboards.findAll();
        model.addAttribute("allCPUs", allCPUs);
        model.addAttribute("allMOBOS", allMOBOS);
        model.addAttribute("pcBuild", new PcBuild()); // Empty build object
        return "user/addbuild";
    }

    // Handle the POST request to add a new PC build
    @PostMapping("/addbuild")
    public String addPcBuildPost(Model model, PcBuild pcBuild) {
        // Validate compatibility between selected CPU and MOBO
        if (!isCompatible(pcBuild.getSelectedCPU(), pcBuild.getSelectedMOBO())) {
            model.addAttribute("errorMessage", "Selected CPU and motherboard are not compatible.");
            model.addAttribute("allCPUs", processors.findAll());
            model.addAttribute("allMOBOS", motherboards.findAll());
            return "user/addbuild";
        }

        PcBuild savedPcBuild = pcBuildRepository.save(pcBuild);
        return "redirect:/user/pcbuilds";
    }

    // Handle the request to delete a PC build
    @GetMapping("/deletebuild/{id}")
    public String deletePcBuild(@PathVariable Integer id) {
        Optional<PcBuild> pcBuild = pcBuildRepository.findById(id);
        if (pcBuild.isPresent()) {
            pcBuildRepository.deleteById(id);
        }
        return "redirect:/user/pcbuilds";
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
        return "user/pcbuilds"; // Display all PC builds
    }
}