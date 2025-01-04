package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class MOBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String manufacturer;
    private String chipset;
    private Integer wattageUsage;
    private String moboFormFactor;
    private String memoryType;
    private String interfaceType;
    private String socketType;
    private boolean m2Support;
    private Double price;

    // Many-to-One relationship with PcBuild (A MOBO can be part of many builds)
    @OneToMany(mappedBy = "selectedMOBO")
    private List<PcBuild> pcBuilds = new ArrayList<>();

    //    // Many-to-Many relationship with CPU (A motherboard can be compatible with many CPUs)
//    @ManyToMany(mappedBy = "compatibleMOBOs")
//    private List<CPU> compatibleCPUs;
    public boolean isCompatibleWith(CPU cpu) {

        return this.socketType.equals(cpu.getSocketType());
    }

    // Method to check compatibility with the motherboard
    public boolean isCompatibleWith(MOBO mobo) {
        if (mobo == null) {
            return false;
        }
        // Check if the memory type is supported by the motherboard
        boolean isMemoryTypeCompatible = this.memoryType.equals(mobo.getMemoryType());
        return isMemoryTypeCompatible;
    }

    // Getters and setters for all fields
    public List<PcBuild> getPcBuilds() {
        return pcBuilds;
    }

    public void setPcBuilds(List<PcBuild> pcBuilds) {
        this.pcBuilds = pcBuilds;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public Integer getWattageUsage() {
        return wattageUsage;
    }

    public void setWattageUsage(Integer wattageUsage) {
        this.wattageUsage = wattageUsage;
    }

    public String getMoboFormFactor() {
        return moboFormFactor;
    }

    public void setMoboFormFactor(String moboFormFactor) {
        this.moboFormFactor = moboFormFactor;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getSocketType() {
        return socketType;
    }

    public void setSocketType(String socketType) {
        this.socketType = socketType;
    }

    public boolean isM2Support() {
        return m2Support;
    }

    public void setM2Support(boolean m2Support) {
        this.m2Support = m2Support;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
