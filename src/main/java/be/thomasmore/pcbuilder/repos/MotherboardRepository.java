package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MotherboardRepository extends CrudRepository<MOBO, Integer> {

    @Query("select m from MOBO m WHERE " +
            ":searchWord IS NULL OR " +
            "(m.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(m.manufacturer ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(m.chipset ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(m.memoryType ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(m.moboFormFactor ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(m.interfaceType ILIKE CONCAT('%', :searchWord, '%'))")
    List<MOBO> findBySearch(
            @Param("searchWord") String searchWord);


    @Query("SELECT m FROM MOBO m WHERE " +
            "(:filterManufacturer IS NULL OR :filterManufacturer = m.manufacturer) OR " +
            "(:filterSocket IS NULL OR :filterSocket = m.socketType) OR " +
            "(:filterChipset IS NULL OR :filterChipset = m.chipset) OR " +
            "(:filterMemory IS NULL OR :filterMemory = m.memoryType) OR " +
            "(:filterMoboFormFactor IS NULL OR :filterMoboFormFactor = m.moboFormFactor)")
    List<MOBO> findByFilter(
            @Param("filterManufacturer") String filterManufacturer,
            @Param("filterSocket") String filterSocket,
            @Param("filterChipset") String filterChipset,
            @Param("filterMemory") String filterMemory,
            @Param("filterMoboFormFactor") String filterMoboFormFactor);


    @Query("SELECT m FROM MOBO m WHERE " +
            "(:filterMinPrice IS NULL OR m.price >= :filterMinPrice) AND " +
            "(:filterMaxPrice IS NULL OR m.price <= :filterMaxPrice)")
    List<MOBO> findByPrice(
            @Param("filterMinPrice") Double filterMinPrice,
            @Param("filterMaxPrice") Double filterMaxPrice);

    @Query("SELECT m FROM MOBO m")
    List<MOBO> findByAll();
}
