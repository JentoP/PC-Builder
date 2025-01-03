package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pc_builds")
public class PcBuild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer buildId;

    private String buildName;

    @ManyToOne
    private User user;

    // Many-to-One relationship with CPU: Each build has exactly one CPU
    @ManyToOne
    @JoinColumn(name = "cpu_id", nullable = false)
    private CPU selectedCPU;

    // Many-to-One relationship with MOBO: Each build has exactly one MOBO
    @ManyToOne
    @JoinColumn(name = "mobo_id", nullable = false)
    private MOBO selectedMOBO;

    // Getters and setters for all fields

    public Integer getId() {
        return buildId;
    }

    public void setId(Integer id) {
        this.buildId = id;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CPU getSelectedCPU() {
        return selectedCPU;
    }

    public void setSelectedCPU(CPU selectedCPU) {
        this.selectedCPU = selectedCPU;
    }

    public MOBO getSelectedMOBO() {
        return selectedMOBO;
    }

    public void setSelectedMOBO(MOBO selectedMOBO) {
        this.selectedMOBO = selectedMOBO;
    }
}
