package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class COOLER {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String manufacturer;
    private boolean waterCooling;
    private Integer fanSize;
    private Integer radiatorSize;
    private boolean RGB;
    private String socketType;
    private String color;
    private Double price;

    // Inverse side of the relationship to PcBuild
    @OneToMany(mappedBy = "selectedCooler", fetch = FetchType.LAZY)
    private List<PcBuild> pcBuilds;

    public List<PcBuild> getPcBuilds() {
        return pcBuilds;
    }

    public void setPcBuilds(List<PcBuild> pcBuilds) {
        this.pcBuilds = pcBuilds;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSocketType() {
        return socketType;
    }

    public void setSocketType(String socketType) {
        this.socketType = socketType;
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

    public boolean isWaterCooling() {
        return waterCooling;
    }

    public void setWaterCooling(boolean waterCooling) {
        this.waterCooling = waterCooling;
    }

    public Integer getFanSize() {
        return fanSize;
    }

    public void setFanSize(Integer fanSize) {
        this.fanSize = fanSize;
    }

    public Integer getRadiatorSize() {
        return radiatorSize;
    }

    public void setRadiatorSize(Integer radiatorSize) {
        this.radiatorSize = radiatorSize;
    }

    public boolean isRGB() {
        return RGB;
    }

    public void setRGB(boolean RGB) {
        this.RGB = RGB;
    }

}
