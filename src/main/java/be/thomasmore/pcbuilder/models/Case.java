package be.thomasmore.pcbuilder.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Case {
    @Id
    private Integer id;
    private String name;
    private String manufacturer;
    private boolean sidePanel;
    private String moboFormFactor;
    private String psuFormFactor;
    private String color;

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
