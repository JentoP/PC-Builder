package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MotherboardRepository extends CrudRepository<MOBO, Integer> {
    @Query("select m from MOBO m WHERE " +
            ":searchWord IS NULL OR " +
            "(m.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(m.manufacturer ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(m.chipset ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(m.memoryType ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(m.moboFormFactor ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(m.interfaceType ILIKE CONCAT('%', :searchWord, '%'))")
    List<MOBO> findBySearch(@Param("searchWord") String searchWord);
}
