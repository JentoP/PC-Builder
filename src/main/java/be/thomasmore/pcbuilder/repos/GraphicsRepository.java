package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GraphicsRepository extends CrudRepository<GPU, Integer> {
    @Query("select g from GPU g WHERE " +
            ":searchWord IS NULL OR " +
            "(g.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(g.manufacturer ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(g.gpuModel ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(g.interfaceType ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(g.chipset ILIKE CONCAT('%', :searchWord, '%'))")
    List<GPU> findBySearch(@Param("searchWord") String searchWord);
}
