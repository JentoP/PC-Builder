package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public class RAM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String manufacturer;
    private Integer wattageUsage;
    private Integer memoryCapacity;
    private String memoryType;
    private Integer timings;
    private Integer clockSpeed;
    private Double price;

    @OneToMany(mappedBy = "selectedMemory", fetch = FetchType.LAZY)
    private Collection<PcBuild> pcBuild;

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

    public Integer getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(Integer memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public Integer getTimings() {
        return timings;
    }

    public void setTimings(Integer timings) {
        this.timings = timings;
    }

    public Integer getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(Integer clockSpeed) {
        this.clockSpeed = clockSpeed;
    }


    public Collection<PcBuild> getPcBuild() {
        return pcBuild;
    }

    public void setPcBuild(Collection<PcBuild> pcBuild) {
        this.pcBuild = pcBuild;
    }
}
