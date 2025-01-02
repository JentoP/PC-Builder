package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.CPU;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProcessorRepository extends CrudRepository<CPU, Integer> {

    @Query("select c from CPU c WHERE " +
            ":searchWord IS NULL OR " +
            "(c.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(c.manufacturer ILIKE CONCAT('%', :searchWord, '%'))")
    List<CPU> findBySearch(
            @Param("searchWord") String searchWord);

    @Query("SELECT c FROM CPU c WHERE " +
            "(:filterManufacturer IS NULL OR :filterManufacturer = c.manufacturer) OR " +
            "(:filterSocket IS NULL OR :filterSocket = c.socketType) OR " +
            "(:filterCore IS NULL OR :filterCore = c.coreCount) OR " +
            "(:filterArchitecture IS NULL OR :filterArchitecture = c.architecture)")
    List<CPU> findByFilter(
            @Param("filterManufacturer") String filterManufacturer,
            @Param("filterSocket") String filterSocket,
            @Param("filterCore") String filterCore,
            @Param("filterArchitecture") String filterArchitecture);

    @Query("SELECT c FROM CPU c WHERE " +
            "(:filterMinPrice IS NULL OR c.price >= :filterMinPrice) AND " +
            "(:filterMaxPrice IS NULL OR c.price <= :filterMaxPrice)")
    List<CPU> findByPrice(
            @Param("filterMinPrice") Double filterMinPrice,
            @Param("filterMaxPrice") Double filterMaxPrice);
}
