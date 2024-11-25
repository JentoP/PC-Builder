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
    List<MOBO> findBySearch(@Param("searchWord") String searchWord);

    @Query("SELECT m FROM MOBO m WHERE :filterManufacturer IS NULL OR :filterManufacturer = m.manufacturer")
    List<MOBO> findByManufacturer(@Param("filterManufacturer") String filterManufacturer);

    @Query("SELECT m FROM MOBO m WHERE " +
            "(:filterMinPrice IS NULL OR m.price >= :filterMinPrice) AND " +
            "(:filterMaxPrice IS NULL OR m.price <= :filterMaxPrice)")
    List<MOBO> findByPrice(@Param("filterMinPrice") Double filterMinPrice, @Param("filterMaxPrice") Double filterMaxPrice);

    @Query("SELECT m FROM MOBO m WHERE :filterSocket IS NULL OR :filterSocket = m.socketType")
    List<MOBO> findBySocket(@Param("filterSocket") String filterSocket);

    @Query("SELECT m FROM MOBO m WHERE :filterChipset IS NULL OR :filterChipset = m.chipset")
    List<MOBO> findByChipset(@Param("filterChipset") String filterChipset);

    @Query("SELECT m FROM MOBO m WHERE :filterMemory IS NULL OR :filterMemory = m.memoryType")
    List<MOBO> findByMemoryType(@Param("filterMemory") String filterMemory);

    @Query("SELECT m FROM MOBO m WHERE :filterMoboFormFactor IS NULL OR :filterMoboFormFactor = m.moboFormFactor")
    List<MOBO> findByMoboFormFactor(@Param("filterMoboFormFactor") String filterMoboFormFactor);
}
