package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.COOLING;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoolingRepository extends CrudRepository<COOLING, Integer> {

    @Query("select c from COOLING c WHERE " +
            ":searchWord IS NULL OR " +
            "(c.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(c.manufacturer ILIKE CONCAT('%', :searchWord, '%'))")
    List<COOLING> findBySearch(
            @Param("searchWord") String searchWord);

    @Query("SELECT c FROM COOLING c WHERE " +
            "(:filterManufacturer IS NULL OR :filterManufacturer = c.manufacturer) AND " +
            "(:filterSocketType IS NULL OR :filterSocketType = c.socketType) AND " +
            "(:filterFanSize IS NULL OR :filterFanSize = c.fanSize) AND " +
            "(:filterRadiatorSize IS NULL OR :filterRadiatorSize = c.radiatorSize)")
    List<COOLING> findByFilter(
            @Param("filterManufacturer") String filterManufacturer,
            @Param("filterSocketType") String filterSocketType,
            @Param("filterFanSize") String filterFanSize,
            @Param("filterRadiatorSize") String filterRadiatorSize);

    @Query("SELECT c FROM COOLING c WHERE " +
            "(:filterMinPrice IS NULL OR c.price >= :filterMinPrice) AND " +
            "(:filterMaxPrice IS NULL OR c.price <= :filterMaxPrice)")
    List<COOLING> findByPrice(
            @Param("filterMinPrice") Double filterMinPrice,
            @Param("filterMaxPrice") Double filterMaxPrice);
}
