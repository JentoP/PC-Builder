package be.thomasmore.pcbuilder.controllers;

import be.thomasmore.pcbuilder.models.*;
import be.thomasmore.pcbuilder.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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


    @PostMapping("/builder")
    public String createPcBuild(@ModelAttribute PcBuild pcBuild) {
        // Access selected CPU, MOBO, and other components
        CPU selectedCPU = pcBuild.getSelectedCPU();
        MOBO selectedMOBO = pcBuild.getSelectedMOBO();
        // Process the selected components (e.g., save the build)
        // Logic to save the PC build or redirect
        return "redirect:/pcbuilds";
    }

    @GetMapping("/addbuild")
    public String showAddBuildForm(Model model) {
        // Fetch available components from repositories
        List<CPU> allCPUs = (List<CPU>) processors.findAll();
        List<MOBO> allMOBOS = (List<MOBO>) motherboards.findAll();
//        List<Chassis> allChassis = (List<Chassis>) cases.findAll();
//        List<Cooling> allCoolingSolutions = (List<Cooling>) coolingSolutions.findAll();
//        List<Graphics> allGraphicsCards = (List<Graphics>) graphicCards.findAll();
//        List<Memory> allMemoryKits = (List<Memory>) memoryKits.findAll();
//        List<Power> allPowerSupplies = (List<Power>) powerSupplies.findAll();
//        List<Storage> allStorage = (List<Storage>) storage.findAll();

        model.addAttribute("pcBuild", new PcBuild());
        model.addAttribute("allCPUs", allCPUs);
        model.addAttribute("allMOBOS", allMOBOS);
//        model.addAttribute("allChassis", allChassis);
//        model.addAttribute("allCoolingSolutions", allCoolingSolutions);
//        model.addAttribute("allGraphicsCards", allGraphicsCards);
//        model.addAttribute("allMemoryKits", allMemoryKits);
//        model.addAttribute("allPowerSupplies", allPowerSupplies);
//        model.addAttribute("allStorage", allStorage);

        return "addBuild";
    }
    @GetMapping("/pcbuilds")
    public String listPcBuilds(Model model) {
        // List all the saved PC builds
//        List<PcBuild> pcBuilds = (List<PcBuild>) pcBuildRepository.findAll();
//        model.addAttribute("pcBuilds", pcBuilds);
        return "pcBuildList"; // A template that lists all builds
    }
//    public void createFile() {
//        String filePath = "C:\\Users\\thoma\\Desktop\\build.txt";
//        String content = "Build created at: " + LocalDateTime.now();
//        try {
//            FileWriter pcBuild = new FileWriter(filePath);
//            pcBuild.write(content);
//        } catch (FileNotFoundException e) {
//            System.out.println("Could not locate file location.");
//        } catch (IOException e) {
//            System.out.println("Error creating file: " + e.getMessage());
//        }
//    }
}
