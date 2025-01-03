package be.thomasmore.pcbuilder.controllers;

import be.thomasmore.pcbuilder.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
public class BuildController {
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


    @GetMapping("/builder")
    public String pcBuilder(Model model) {
//        model.addAttribute("allProcessors", processors.findAll());
//        model.addAttribute("allMotherboards", motherboards.findAll());
//        model.addAttribute("allCases", cases.findAll());
//        model.addAttribute("allCooling", coolingSolutions.findAll());
//        model.addAttribute("allGraphicCards", graphicCards.findAll());
//        model.addAttribute("allMemory", memoryKits.findAll());
//        model.addAttribute("allPowerSupplies", powerSupplies.findAll());
//        model.addAttribute("allStorage", storage.findAll());

        return "builder";
    }

    public void createFile() {
        String filePath = "C:\\Users\\thoma\\Desktop\\build.txt";
        String content = "Build created at: " + LocalDateTime.now();
        try {
            FileWriter pcBuild = new FileWriter(filePath);
            pcBuild.write(content);
        }

        catch (FileNotFoundException e) {
            System.out.println("Could not locate file location.");
        }
        catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
}
