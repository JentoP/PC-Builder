package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

/*
 * @author Jento Pieters
 *
 * Entity and Discriminator:
 * The @Entity and @DiscriminatorValue("MOBO") annotations mark this class as a JPA entity and set the discriminator value to "MOBO."
 *
 */
@Entity
@DiscriminatorValue("MOBO")
public class MOBO extends Component {
//    private Integer id;
    private String name;
    private String manufacturer;
    private String chipset;
    private Integer wattageUsage;
    private String moboFormFactor;
    private String memoryType;
    private String interfaceType;
    private String socketType;
    private boolean m2Support;
    private Double price;

    /*
     * Compatibility Check:
     * The isCompatibleWith method checks if the other component is a CPU and compares the socket types for compatibility.
     */
    @Override
    public boolean isCompatibleWith(Component other) {
        if (other instanceof CPU) {
            return ((CPU) other).getSocketType().equals(this.socketType);
        } // Add compatibility checks for other components like RAM, GPU, etc. return true
        return true;
    }

    //Getters and setters
    public boolean hasM2Support() {
        return m2Support;
    }

    public void setM2Support(boolean m2Support) {
        this.m2Support = m2Support;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

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

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public Integer getWattageUsage() {
        return wattageUsage;
    }

    public void setWattageUsage(Integer wattageUsage) {
        this.wattageUsage = wattageUsage;
    }

    public String getMoboFormFactor() {
        return moboFormFactor;
    }

    public void setMoboFormFactor(String moboFormFactor) {
        this.moboFormFactor = moboFormFactor;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getSocketType() {
        return socketType;
    }

    public void setSocketType(String socketType) {
        this.socketType = socketType;
    }
}
