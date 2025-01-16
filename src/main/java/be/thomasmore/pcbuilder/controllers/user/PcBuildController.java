package be.thomasmore.pcbuilder.controllers.user;

import be.thomasmore.pcbuilder.models.*;
import be.thomasmore.pcbuilder.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Controller for handling PC build operations including creating, editing, viewing,
 * deleting, and listing PC builds.
 */
@Controller
@RequestMapping("/user")
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
    private PcBuildRepository pcBuilds;
    @Autowired
    private UserRepository users;

    /**
     * Creates or retrieves a PC build based on the provided ID.
     * If the ID is null, a new PC build is created.
     *
     * @param id the ID of the PC build to retrieve, can be null for a new build.
     * @return the PcBuild object to be used in the view.
     */
    @ModelAttribute("pcBuild")
    public PcBuild createPcBuild(@PathVariable(required = false) Integer id) {
        if (id == null) {
            return new PcBuild();
        }
        Optional<PcBuild> optionalPcBuild = pcBuilds.findById(id);
        return optionalPcBuild.orElse(null);
    }

    /**
     * Adds or edits a PC build depending on whether an ID is provided.
     * This method retrieves the relevant components from repositories
     * and prepares them for use in the view.
     *
     * @param id    the ID of the PC build to edit, can be null for adding a new build.
     * @param model the model object to store attributes for the view.
     * @return the name of the template to render.
     */
    @GetMapping({"/addbuild", "/editbuild/{id}"})
    public String addOrEditPcBuild(@PathVariable(required = false) Integer id, Model model) {
        PcBuild pcBuild = createPcBuild(id);  // Fetch the build or create a new one
        model.addAttribute("pcBuild", pcBuild);

        // Retrieve all components from repositories
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

        // Add a flag to distinguish between "edit" and "add" modes in the template
        model.addAttribute("isEditMode", id != null);

        return "user/managebuild";  // Use the same template for both add and edit operations.
    }

    /**
     * Handles the POST request to either add a new PC build or edit an existing one.
     * This method also validates the compatibility of selected components.
     *
     * @param pcBuild            the PC build object containing selected components.
     * @param redirectAttributes the redirect attributes to pass flash messages.
     * @return a redirect URL based on the success or failure of the operation.
     */
    @PostMapping({"/addbuild", "/editbuild/{id}"})
    public String addOrEditPcBuildPost(@ModelAttribute PcBuild pcBuild, RedirectAttributes redirectAttributes, Principal principal) {
        // Validate compatibility between selected CPU and MOBO
        if (!isCompatible(pcBuild)) {
            redirectAttributes.addFlashAttribute("errorMessage", "De geselecteerde componenten zijn niet compatibel met elkaar. Probeer opnieuw.");
            if (pcBuild.getBuildId() != null) {
                return "redirect:/user/editbuild/" + pcBuild.getBuildId();
            }
            return "redirect:/user/addbuild";
        }
        // Associate the build with the logged-in user
        User user = users.findByUsername(principal.getName());
        pcBuild.setUsername(user);

        // Save the new or updated PC build
        pcBuilds.save(pcBuild);
        redirectAttributes.addFlashAttribute("successMessage", "Uw build werd opgeslagen!");
        return "redirect:/user/pcbuilds"; // Redirect to the list of PC builds
    }

    /**
     * Views the details of a specific PC build.
     *
     * @param id    the ID of the PC build to view.
     * @param model the model object to store attributes for the view.
     * @return the name of the template to render.
     */
    @GetMapping("/viewbuild/{id}")
    public String viewPcBuild(@PathVariable Integer id, Model model, Principal principal) {

        User user = users.findByUsername(principal.getName());

        Optional<PcBuild> optionalPcBuild = pcBuilds.findByIdAndUser(id, user);
        if (optionalPcBuild.isPresent()) {
            PcBuild pcBuild = optionalPcBuild.get();
            Integer totalWattage = calculateTotalWattage(pcBuild);
            model.addAttribute("pcBuild", pcBuild);
            model.addAttribute("totalWattage", totalWattage);
            return "user/viewbuild"; // Show build details
        }
        return "redirect:/pcbuilds";
    }

    /**
     * Lists all PC builds.
     *
     * @param model the model object to store attributes for the view.
     * @return the name of the template to render.
     */
    @GetMapping("/pcbuilds")
    public String viewPcBuilds(Model model, Principal principal) {
        User user = users.findByUsername(principal.getName());
        List<PcBuild> allPcBuild = pcBuilds.findAllByUser(user);
        model.addAttribute("pcBuilds", allPcBuild);
        return "user/pcbuilds"; // Display all PC builds from matching user
    }

    /**
     * Handles the request to delete a PC build.
     *
     * @param id the ID of the PC build to delete.
     * @return a redirect URL to the list of PC builds.
     */
    @GetMapping("/deletebuild/{id}")
    public String deletePcBuild(@PathVariable Integer id) {
        Optional<PcBuild> pcBuild = pcBuilds.findById(id);
        if (pcBuild.isPresent()) {
            pcBuilds.deleteById(id);
        }
        return "redirect:/user/pcbuilds";
    }

    /**
     * Checks if the selected components are compatible with each other.
     *
     * @param pcBuild the PC build object containing selected components.
     * @return true if all selected components are compatible, false otherwise.
     */
    private boolean isCompatible(PcBuild pcBuild) {
        CPU selectedCPU = pcBuild.getSelectedCPU();
        MOBO selectedMOBO = pcBuild.getSelectedMOBO();
        RAM selectedMemory = pcBuild.getSelectedMemory();
        GPU selectedGPU = pcBuild.getSelectedGPU();
        PSU selectedPowerSupply = pcBuild.getSelectedPowerSupply();
        CHASSIS selectedCase = pcBuild.getSelectedCase();

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
        return true;
    }

    /**
     * Helper method to calculate the total wattage usage of the selected components.
     *
     * @param pcBuild the PC build object containing selected components.
     * @return the total wattage usage of the selected components.
     */
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
        return totalWattage;
    }

}
