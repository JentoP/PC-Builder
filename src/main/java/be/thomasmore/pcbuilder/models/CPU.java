package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.util.Collection;

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

    @OneToMany(mappedBy = "cpu", fetch = FetchType.LAZY)
    private Collection<Build> builds;

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


    public Collection<Build> getBuilds() {
        return builds;
    }

    public void setBuilds(Collection<Build> builds) {
        this.builds = builds;
    }
}
