package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pcbuilds")
public class PcBuild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "build_name", length = 30, nullable = false)
    private String buildName;

    // Many-to-one relationships for other components
    @ManyToOne
    @JoinColumn(name = "cpu_id", nullable = false)
    private CPU selectedCPU;

    @ManyToOne
    @JoinColumn(name = "mobo_id", nullable = false)
    private MOBO selectedMOBO;

    @ManyToOne
    @JoinColumn(name = "chassis_id", nullable = false)
    private CHASSIS selectedCase;

    @ManyToOne
    @JoinColumn(name = "cooler_id", nullable = false)
    private COOLER selectedCooler;

    @ManyToOne
    @JoinColumn(name = "gpu_id", nullable = false)
    private GPU selectedGPU;

    @ManyToOne
    @JoinColumn(name = "ram_id", nullable = false)
    private RAM selectedMemory;

    @ManyToOne
    @JoinColumn(name = "storage_id", nullable = false)
    private DATA selectedStorage;

    @ManyToOne
    @JoinColumn(name = "psu_id", nullable = false)
    private PSU selectedPowerSupply;

    private Double totalPrice;
    private Integer memoryQuantities;
    private Integer storageQuantities;

    public Double calculateTotalPrice() {
        // Calculating total price by adding all the selected component prices
        totalPrice = 0.0;

        if (selectedCPU != null) totalPrice += selectedCPU.getPrice();
        if (selectedMOBO != null) totalPrice += selectedMOBO.getPrice();
        if (selectedCase != null) totalPrice += selectedCase.getPrice();
        if (selectedCooler != null) totalPrice += selectedCooler.getPrice();
        if (selectedGPU != null) totalPrice += selectedGPU.getPrice();
        if (selectedMemory != null) totalPrice += selectedMemory.getPrice() * memoryQuantities;
        if (selectedStorage != null) totalPrice += selectedStorage.getPrice() * storageQuantities;
        if (selectedPowerSupply != null) totalPrice += selectedPowerSupply.getPrice();

        return totalPrice;
    }

    // Getters and setters

    public Integer getBuildId() {
        return id;
    }

    public void setBuildId(Integer id) {
        this.id = id;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public CPU getSelectedCPU() {
        return selectedCPU;
    }

    public void setSelectedCPU(CPU selectedCPU) {
        this.selectedCPU = selectedCPU;
    }

    public MOBO getSelectedMOBO() {
        return selectedMOBO;
    }

    public void setSelectedMOBO(MOBO selectedMOBO) {
        this.selectedMOBO = selectedMOBO;
    }

    public CHASSIS getSelectedCase() {
        return selectedCase;
    }

    public void setSelectedCase(CHASSIS selectedCase) {
        this.selectedCase = selectedCase;
    }

    public COOLER getSelectedCooler() {
        return selectedCooler;
    }

    public void setSelectedCooler(COOLER selectedCooler) {
        this.selectedCooler = selectedCooler;
    }

    public GPU getSelectedGPU() {
        return selectedGPU;
    }

    public void setSelectedGPU(GPU selectedGPU) {
        this.selectedGPU = selectedGPU;
    }

    public RAM getSelectedMemory() {
        return selectedMemory;
    }

    public void setSelectedMemory(RAM selectedMemory) {
        this.selectedMemory = selectedMemory;
    }

    public DATA getSelectedStorage() {
        return selectedStorage;
    }

    public void setSelectedStorage(DATA selectedStorage) {
        this.selectedStorage = selectedStorage;
    }

    public PSU getSelectedPowerSupply() {
        return selectedPowerSupply;
    }

    public void setSelectedPowerSupply(PSU selectedPowerSupply) {
        this.selectedPowerSupply = selectedPowerSupply;
    }

    public Integer getStorageQuantities() {
        return storageQuantities;
    }

    public void setStorageQuantities(Integer storageQuantities) {
        this.storageQuantities = storageQuantities;
    }

    public Integer getMemoryQuantities() {
        return memoryQuantities;
    }

    public void setMemoryQuantities(Integer memoryQuantities) {
        this.memoryQuantities = memoryQuantities;
    }
}
