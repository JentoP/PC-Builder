package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.io.FileWriter;
import java.io.IOException;

@Entity
@Table(name = "pcbuilds")
public class PcBuild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public Double calculateTotalPrice() {
        // Calculating total price by adding all the selected component prices
        totalPrice = 0.0;

        if (selectedCPU != null) totalPrice += selectedCPU.getPrice();
        if (selectedMOBO != null) totalPrice += selectedMOBO.getPrice();
        if (selectedCase != null) totalPrice += selectedCase.getPrice();
        if (selectedCooler != null) totalPrice += selectedCooler.getPrice();
        if (selectedGPU != null) totalPrice += selectedGPU.getPrice();
        if (selectedMemory != null) totalPrice += selectedMemory.getPrice();
        if (selectedStorage != null) totalPrice += selectedStorage.getPrice();
        if (selectedPowerSupply != null) totalPrice += selectedPowerSupply.getPrice();

        return totalPrice;
    }
    public void printToTextFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("PC Build Details\n");
            writer.write("=================\n");
            writer.write("Build Name: " + buildName + "\n");
            writer.write("Processor: " + (selectedCPU != null ? selectedCPU.getName() : "None") + "\n");
            writer.write("Motherboard: " + (selectedMOBO != null ? selectedMOBO.getName() : "None") + "\n");
            writer.write("Case: " + (selectedCase != null ? selectedCase.getName() : "None") + "\n");
            writer.write("Cooler: " + (selectedCooler != null ? selectedCooler.getName() : "None") + "\n");
            writer.write("GPU: " + (selectedGPU != null ? selectedGPU.getName() : "None") + "\n");
            writer.write("Memory: " + (selectedMemory != null ? selectedMemory.getName() : "None") + "\n");
            writer.write("Storage: " + (selectedStorage != null ? selectedStorage.getName() : "None") + "\n");
            writer.write("Power Supply: " + (selectedPowerSupply != null ? selectedPowerSupply.getName() : "None") + "\n");
            writer.write("Total Price: " + (totalPrice != null ? String.format("%.2f", totalPrice) : "0.00") + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
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

    public Double getTotalPrice() {
        return totalPrice;
    }
}
