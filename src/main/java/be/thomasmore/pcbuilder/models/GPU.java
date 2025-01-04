package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public class GPU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private String manufacturer;
    private Integer wattageUsage;
    private String gpuModel;
    private String chipset;
    private Integer memoryCapacity;
    private String interfaceType;
    private String clockSpeed;
    private String color;
    private Double price;


    // Inverse side of the relationship to PcBuild
    @OneToMany(mappedBy = "selectedGPU")
    private List<PcBuild> pcBuilds;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getWattageUsage() {
        return wattageUsage;
    }

    public void setWattageUsage(Integer wattageUsage) {
        this.wattageUsage = wattageUsage;
    }

    public String getGpuModel() {
        return gpuModel;
    }

    public void setGpuModel(String gpuModel) {
        this.gpuModel = gpuModel;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public Integer getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(Integer memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(String clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


//    public Collection<PcBuild> getBuilds() {
//        return pcBuilds;
//    }
//
//    public void setBuilds(Collection<PcBuild> pcBuilds) {
//        this.pcBuilds = pcBuilds;
//    }
}
