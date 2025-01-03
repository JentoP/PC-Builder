package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Jento Pieters
 *
 * Entity Annotation: The @Entity annotation defines PcBuild as a JPA entity.
 *
 * Relationships:
 * @ManyToOne establishes a many-to-one relationship with the User entity.
 * @ManyToMany with @JoinTable sets up the many-to-many relationship between PcBuild and Component, specifying the join table and columns.
 */
@Entity
public class PcBuild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String buildName;
    @ManyToOne
    private User user;
    @ManyToMany
    @JoinTable(
            name = "build_components",
            joinColumns = @JoinColumn(name = "build_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    private List<Component> components = new ArrayList<>();

    public boolean addComponent(Component component) {
        for (Component existingComponent : components) {
            if (!existingComponent.isCompatibleWith(component)) {
                return false;
            }
        }
        components.add(component);
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
