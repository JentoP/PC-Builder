package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
//
//    @OneToMany(mappedBy = "user")  // One user can have multiple builds
//    private List<PcBuild> pcBuilds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public List<PcBuild> getPcBuilds() {
//        return pcBuilds;
//    }

//    public void setPcBuilds(List<PcBuild> pcBuilds) {
//        this.pcBuilds = pcBuilds;
//    }
}
