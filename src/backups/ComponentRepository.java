package be.thomasmore.pcbuilder.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import be.thomasmore.pcbuilder.models.Component;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComponentRepository extends JpaRepository<Component, Integer> {
    @Query("SELECT c FROM Component c JOIN c.compatibleComponents cc WHERE cc = :component")
    List<Component> findByCompatibleComponents(@Param("component") Component component);
}
