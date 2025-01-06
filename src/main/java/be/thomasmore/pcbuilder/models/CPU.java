package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CPU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String manufacturer;
    private String architecture;
    private Integer wattageUsage;
    private String socketType;
    private String cpuModel;
    private Integer coreCount;
    private Integer clockSpeed;
    private Double price;

    // Many-to-One relationship with PcBuild (A CPU can be part of many builds)
    @OneToMany(mappedBy = "selectedCPU")
    private List<PcBuild> pcBuilds = new ArrayList<>();

    // Getters and setters for all fields
    public List<PcBuild> getPcBuilds() {
        return pcBuilds;
    }

    /**
     * @param pcBuilds A list of PcBuild objects
     */
    public void setPcBuilds(List<PcBuild> pcBuilds) {
        this.pcBuilds = pcBuilds;
    }

    public boolean isCompatibleWith(MOBO mobo) {
        return this.socketType.equals(mobo.getSocketType());
    }

    // Getters and setters for all fields
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public Integer getWattageUsage() {
        return wattageUsage;
    }

    public void setWattageUsage(Integer wattageUsage) {
        this.wattageUsage = wattageUsage;
    }

    public String getSocketType() {
        return socketType;
    }

    public void setSocketType(String socketType) {
        this.socketType = socketType;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public Integer getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(Integer coreCount) {
        this.coreCount = coreCount;
    }

    public Integer getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(Integer clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
