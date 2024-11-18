package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StorageRepository extends CrudRepository<DATA, Integer> {
    @Query("select d from DATA d WHERE " +
            ":searchWord IS NULL OR " +
            "(d.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(d.manufacturer ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(d.interfaceType ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(d.storageType ILIKE CONCAT('%', :searchWord, '%'))")
    List<DATA> findBySearch(@Param("searchWord") String searchWord);
}
