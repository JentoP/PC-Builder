package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoolingRepository extends CrudRepository<COOLING, Integer> {
    @Query("select c from COOLING c WHERE " +
            ":searchWord IS NULL OR " +
            "(c.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(c.manufacturer ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(c.socketType ILIKE CONCAT('%', :searchWord, '%'))")
    List<COOLING> findBySearch(@Param("searchWord") String searchWord);
}
