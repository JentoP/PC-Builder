package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class DATA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String manufacturer;
    private String dataModel;
    private String interfaceType;
    private String storageType;
    private Integer wattageUsage;
    private Integer capacity;
    private Integer cacheMemory;
    private Double price;
    // Inverse side of the relationship to PcBuild
    @OneToMany(mappedBy = "selectedStorage", fetch = FetchType.LAZY)
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

    public String getDataModel() {
        return dataModel;
    }

    public void setDataModel(String dataModel) {
        this.dataModel = dataModel;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public Integer getWattageUsage() {
        return wattageUsage;
    }

    public void setWattageUsage(Integer wattageUsage) {
        this.wattageUsage = wattageUsage;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCacheMemory() {
        return cacheMemory;
    }

    public void setCacheMemory(Integer cacheMemory) {
        this.cacheMemory = cacheMemory;
    }
}
