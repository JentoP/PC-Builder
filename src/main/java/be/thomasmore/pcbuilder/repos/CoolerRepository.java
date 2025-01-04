package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.COOLER;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoolerRepository extends CrudRepository<COOLER, Integer> {

    @Query("select c from COOLER c WHERE " +
            ":searchWord IS NULL OR " +
            "(c.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(c.manufacturer ILIKE CONCAT('%', :searchWord, '%'))")
    List<COOLER> findBySearch(
            @Param("searchWord") String searchWord);

    @Query("SELECT c FROM COOLER c WHERE " +
            "(:filterManufacturer IS NULL OR :filterManufacturer = c.manufacturer) OR " +
            "(:filterSocketType IS NULL OR :filterSocketType = c.socketType) OR " +
            "(:filterFanSize IS NULL OR :filterFanSize = c.fanSize) OR " +
            "(:filterRadiatorSize IS NULL OR :filterRadiatorSize = c.radiatorSize)")
    List<COOLER> findByFilter(
            @Param("filterManufacturer") String filterManufacturer,
            @Param("filterSocketType") String filterSocketType,
            @Param("filterFanSize") Integer filterFanSize,
            @Param("filterRadiatorSize") Integer filterRadiatorSize);

    @Query("SELECT c FROM COOLER c WHERE " +
            "(:filterMinPrice IS NULL OR c.price >= :filterMinPrice) OR " +
            "(:filterMaxPrice IS NULL OR c.price <= :filterMaxPrice)")
    List<COOLER> findByPrice(
            @Param("filterMinPrice") Double filterMinPrice,
            @Param("filterMaxPrice") Double filterMaxPrice);
}
