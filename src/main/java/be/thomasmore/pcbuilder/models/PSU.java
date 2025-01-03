package be.thomasmore.pcbuilder.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Collection;

@Entity
public class PSU {
    @Id
    private Integer id;
    private String name;
    private String manufacturer;
    private Integer wattageCapacity;
    private String psuModel;
    private String efficiency;
    private String psuFormFactor;
    private String color;
    private Double price;
//    @OneToMany(mappedBy = "powerSupply", fetch = FetchType.LAZY)
//    private Collection<PcBuild> pcBuilds;

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


//    public Collection<PcBuild> getBuilds() {
//        return pcBuilds;
//    }
//
//    public void setBuilds(Collection<PcBuild> pcBuilds) {
//        this.pcBuilds = pcBuilds;
//    }
}
