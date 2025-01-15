package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private List<Authority> authorities;


    @OneToMany(mappedBy = "user")  // One user can have multiple builds
    private List<PcBuild> pcBuilds;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PcBuild> getPcBuilds() {
        return pcBuilds;
    }

    public void setPcBuilds(List<PcBuild> pcBuilds) {
        this.pcBuilds = pcBuilds;
    }

//    public List<Authority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(List<Authority> authorities) {
//        this.authorities = authorities;
//    }
}

