package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
public class Build {
    @Id
    private Integer id;
    private Integer userId;
    private LocalDateTime createdAt;
    private String buildName;
    @ManyToOne (fetch = FetchType.LAZY)
    private CHASSIS chassis;
    @ManyToOne (fetch = FetchType.LAZY)
    private MOBO mobo;
    @ManyToOne (fetch = FetchType.LAZY)
    private CPU cpu;
    @ManyToOne (fetch = FetchType.LAZY)
    private RAM ram;
    @ManyToMany (fetch = FetchType.LAZY)
    private Collection<DATA> storage;
    @ManyToOne (fetch = FetchType.LAZY)
    private GPU gpu;
    @ManyToOne (fetch = FetchType.LAZY)
    private COOLING coolingSolution;
    @ManyToOne (fetch = FetchType.LAZY)
    private PSU powerSupply;

    public CHASSIS getChassis() {
        return chassis;
    }

    public void setChassis(CHASSIS chassis) {
        this.chassis = chassis;
    }

    public MOBO getMobo() {
        return mobo;
    }

    public void setMobo(MOBO mobo) {
        this.mobo = mobo;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public GPU getGpu() {
        return gpu;
    }

    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }

    public COOLING getCoolingSolution() {
        return coolingSolution;
    }

    public void setCoolingSolution(COOLING coolingSolution) {
        this.coolingSolution = coolingSolution;
    }

    public PSU getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(PSU powerSupply) {
        this.powerSupply = powerSupply;
    }

    public Collection<DATA> getStorage() {
        return storage;
    }

    public void setStorage(Collection<DATA> storage) {
        this.storage = storage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }
}
