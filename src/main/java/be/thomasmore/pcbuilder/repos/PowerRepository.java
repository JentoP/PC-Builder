package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.PSU;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PowerRepository extends CrudRepository<PSU, Integer> {

    @Query("select p from PSU p WHERE " +
            ":searchWord IS NULL OR " +
            "(p.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(p.manufacturer ILIKE CONCAT('%', :searchWord, '%'))")
    List<PSU> findBySearch(
            @Param("searchWord") String searchWord);

    @Query("SELECT p FROM PSU p WHERE " +
            "(:filterManufacturer IS NULL OR :filterManufacturer = p.manufacturer) AND " +
            "(:filterWattage IS NULL OR :filterWattage = p.wattageCapacity) AND " +
            "(:filterEfficiency IS NULL OR :filterEfficiency = p.efficiency) AND " +
            "(:filterPsuFormFactor IS NULL OR :filterPsuFormFactor = p.psuFormFactor)")
    List<PSU> findByFilter(
            @Param("filterManufacturer") String filterManufacturer,
            @Param("filterWattage") String filterWattage,
            @Param("filterEfficiency") String filterEfficiency,
            @Param("filterPsuFormFactor") String filterPsuFormFactor);

    @Query("SELECT p FROM PSU p WHERE " +
            "(:filterMinPrice IS NULL OR p.price >= :filterMinPrice) AND " +
            "(:filterMaxPrice IS NULL OR p.price <= :filterMaxPrice)")
    List<PSU> findByPrice(
            @Param("filterMinPrice") Double filterMinPrice,
            @Param("filterMaxPrice") Double filterMaxPrice);
}
