package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.RAM;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemoryRepository extends CrudRepository<RAM, Integer> {

    @Query("select r from RAM r WHERE " +
            ":searchWord IS NULL OR " +
            "(r.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(r.manufacturer ILIKE CONCAT('%', :searchWord, '%'))")
    List<RAM> findBySearch(
            @Param("searchWord") String searchWord);

    @Query("SELECT r FROM RAM r WHERE " +
            "(:filterManufacturer IS NULL OR :filterManufacturer = r.manufacturer) OR " +
            "(:filterMemoryCapacity IS NULL OR :filterMemoryCapacity = r.memoryCapacity) OR " +
            "(:filterMemoryType IS NULL OR :filterMemoryType = r.memoryType) OR " +
            "(:filterTimings IS NULL OR :filterTimings = r.timings)")
    List<RAM> findByFilter(
            @Param("filterManufacturer") String filterManufacturer,
            @Param("filterMemoryCapacity") String filterMemoryCapacity,
            @Param("filterMemoryType") String filterMemoryType,
            @Param("filterTimings") String filterTimings);

    @Query("SELECT r FROM RAM r WHERE " +
            "(:filterMinPrice IS NULL OR r.price >= :filterMinPrice) AND " +
            "(:filterMaxPrice IS NULL OR r.price <= :filterMaxPrice)")
    List<RAM> findByPrice(
            @Param("filterMinPrice") Double filterMinPrice,
            @Param("filterMaxPrice") Double filterMaxPrice);
}
