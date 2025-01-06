package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public class PSU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private String manufacturer;
    private Integer wattageCapacity;
    private String psuModel;
    private String efficiency;
    private String psuFormFactor;
    private String color;
    private Double price;

    // Inverse side of the relationship to PcBuild
    @OneToMany(mappedBy = "selectedPowerSupply")
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

    public Integer getWattageCapacity() {
        return wattageCapacity;
    }

    public void setWattageCapacity(Integer wattageCapacity) {
        this.wattageCapacity = wattageCapacity;
    }

    public String getPsuModel() {
        return psuModel;
    }

    public void setPsuModel(String psuModel) {
        this.psuModel = psuModel;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public String getPsuFormFactor() {
        return psuFormFactor;
    }

    public void setPsuFormFactor(String psuFormFactor) {
        this.psuFormFactor = psuFormFactor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
