package be.thomasmore.pcbuilder.services;
import be.thomasmore.pcbuilder.models.Component;
import be.thomasmore.pcbuilder.repos.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ComponentService {
    @Autowired
    private ComponentRepository componentRepository;
    public List<Component> findCompatibleComponents(Component component) {
        return componentRepository.findByCompatibleComponents(component);
    }
    public boolean isCompatible(Component component1, Component component2) {
        return component1.getCompatibleComponents().contains(component2);
    }
    // Other methods for adding, finding components
}

