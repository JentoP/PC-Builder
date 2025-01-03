package be.thomasmore.pcbuilder.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Jento Pieters
 *
 * Entity and Inheritance:
 * The @Entity and @Inheritance(strategy = InheritanceType.JOINED) annotations define the Component as a JPA entity with a joined table inheritance strategy.
 *
 * Many-to-Many Relationship:
 * The @ManyToMany annotation with the @JoinTable establishes a many-to-many relationship for component compatibility.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "component_type")
public abstract class Component {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer componentId;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "component_compatibility",
            joinColumns = @JoinColumn(name = "component_id"),
            inverseJoinColumns = @JoinColumn(name = "compatible_component_id")
    )
    private List<Component> compatibleComponents = new ArrayList<>();

    /*
     * Abstract Method:
     * The isCompatibleWith abstract method ensures that each specific component type will implement its compatibility logic.
     */
    public abstract boolean isCompatibleWith(Component other);

    public Integer getId() {
        return componentId;
    }

    public void setId(Integer id) {
        this.componentId = id;
    }

    public List<Component> getCompatibleComponents() {
        return compatibleComponents;
    }

    public void setCompatibleComponents(List<Component> compatibleComponents) {
        this.compatibleComponents = compatibleComponents;
    }
}
