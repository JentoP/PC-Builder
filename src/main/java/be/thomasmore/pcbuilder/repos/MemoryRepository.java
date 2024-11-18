package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemoryRepository extends CrudRepository<RAM, Integer> {
    @Query("select r from RAM r WHERE " +
            ":searchWord IS NULL OR " +
            "(r.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(r.manufacturer ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(r.memoryType ILIKE CONCAT('%', :searchWord, '%'))")
    List<RAM> findBySearch(@Param("searchWord") String searchWord);
}
