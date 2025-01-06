package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public class CHASSIS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String manufacturer;
    private boolean sidePanel;
    private String moboFormFactor;
    private String psuFormFactor;
    private String color;
    private Double price;

    // Inverse side of the relationship to PcBuild
    @OneToMany(mappedBy = "selectedCase", fetch = FetchType.LAZY)
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

    public boolean isSidePanel() {
        return sidePanel;
    }

    public void setSidePanel(boolean sidePanel) {
        this.sidePanel = sidePanel;
    }

    public String getMoboFormFactor() {
        return moboFormFactor;
    }

    public void setMoboFormFactor(String moboFormFactor) {
        this.moboFormFactor = moboFormFactor;
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
