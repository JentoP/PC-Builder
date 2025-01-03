package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.GPU;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GraphicsRepository extends CrudRepository<GPU, Integer> {

    @Query("select g from GPU g WHERE " +
            ":searchWord IS NULL OR " +
            "(g.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(g.manufacturer ILIKE CONCAT('%', :searchWord, '%'))")
    List<GPU> findBySearch(
            @Param("searchWord") String searchWord);

    @Query("SELECT g FROM GPU g WHERE " +
            "(:filterManufacturer IS NULL OR :filterManufacturer = g.manufacturer) OR " +
            "(:filterChipset IS NULL OR :filterChipset = g.chipset) OR " +
            "(:filterMemoryCapacity IS NULL OR :filterMemoryCapacity = g.memoryCapacity) OR " +
            "(:filterInterfaceType IS NULL OR :filterInterfaceType = g.interfaceType)")
    List<GPU> findByFilter(
            @Param("filterManufacturer") String filterManufacturer,
            @Param("filterChipset") String filterChipset,
            @Param("filterMemoryCapacity") Integer filterMemoryCapacity,
            @Param("filterInterfaceType") String filterInterfaceType);

    @Query("SELECT g FROM GPU g WHERE " +
            "(:filterMinPrice IS NULL OR g.price >= :filterMinPrice) AND " +
            "(:filterMaxPrice IS NULL OR g.price <= :filterMaxPrice)")
    List<GPU> findByPrice(
            @Param("filterMinPrice") Double filterMinPrice,
            @Param("filterMaxPrice") Double filterMaxPrice);
}
