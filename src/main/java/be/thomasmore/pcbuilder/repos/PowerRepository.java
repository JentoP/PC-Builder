package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PowerRepository extends CrudRepository<PSU, Integer> {
    @Query("select p from PSU p WHERE " +
            ":searchWord IS NULL OR " +
            "(p.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(p.manufacturer ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(p.psuModel ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(p.efficiency ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(p.psuFormFactor ILIKE CONCAT('%', :searchWord, '%'))")
    List<PSU> findBySearch(@Param("searchWord") String searchWord);
}
