package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.CHASSIS;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChassisRepository extends CrudRepository<CHASSIS, Integer> {

    @Query("select c from CHASSIS c WHERE " +
            ":searchWord IS NULL OR " +
            "(c.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(c.manufacturer ILIKE CONCAT('%', :searchWord, '%'))")
    List<CHASSIS> findBySearch(
            @Param("searchWord") String searchWord);

    @Query("SELECT c FROM CHASSIS c WHERE " +
            "(:filterManufacturer IS NULL OR :filterManufacturer = c.manufacturer) OR " +
            "(:filterMoboFormFactor IS NULL OR :filterMoboFormFactor = c.moboFormFactor) OR " +
            "(:filterPsuFormFactor IS NULL OR :filterPsuFormFactor = c.psuFormFactor) OR " +
            "(:filterSidePanel IS NULL OR :filterSidePanel = c.sidePanel)")
    List<CHASSIS> findByFilter(
            @Param("filterManufacturer") String filterManufacturer,
            @Param("filterMoboFormFactor") String filterMoboFormFactor,
            @Param("filterPsuFormFactor") String filterPsuFormFactor,
            @Param("filterSidePanel") Boolean filterSidePanel);

    @Query("SELECT c FROM CHASSIS c WHERE " +
            "(:filterMinPrice IS NULL OR c.price >= :filterMinPrice) AND " +
            "(:filterMaxPrice IS NULL OR c.price <= :filterMaxPrice)")
    List<CHASSIS> findByPrice(
            @Param("filterMinPrice") Double filterMinPrice,
            @Param("filterMaxPrice") Double filterMaxPrice);
}
