package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChassisRepository extends CrudRepository<CHASSIS, Integer> {
    @Query("select c from CHASSIS c WHERE " +
            ":searchWord IS NULL OR " +
            "(c.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(c.manufacturer ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(c.psuFormFactor ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(c.moboFormFactor ILIKE CONCAT('%', :searchWord, '%'))")
    List<CHASSIS> findBySearch(@Param("searchWord") String searchWord);

}
