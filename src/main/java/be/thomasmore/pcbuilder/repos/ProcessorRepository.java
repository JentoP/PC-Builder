package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.CPU;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProcessorRepository extends CrudRepository<CPU, Integer> {

    @Query("SELECT p FROM CPU p WHERE " +
            "(:searchWord IS NULL OR " +
            "(p.name ILIKE CONCAT('%', :searchWord, '%')) OR " +
            "(p.manufacturer ILIKE CONCAT('%', :searchWord, '%')) OR " +
            "(p.socketType ILIKE CONCAT('%', :searchWord, '%')) OR " +
            "(p.architecture ILIKE CONCAT('%', :searchWord, '%')) OR " +
            "(p.cpuModel ILIKE CONCAT('%', :searchWord, '%')))")
    List<CPU> findBySearch(@Param("searchWord") String searchWord);

    @Query("SELECT p FROM CPU p WHERE " +
            "(:filterManufacturer IS NULL OR :filterManufacturer = p.manufacturer) OR " +
            "(:filterSocket IS NULL OR :filterSocket = p.socketType) OR " +
            "(:filterCore IS NULL OR :filterCore = p.coreCount) OR " +
            "(:filterArchitecture IS NULL OR :filterArchitecture = p.architecture) OR " +
            "(:filterCpuModel IS NULL OR :filterCpuModel = p.cpuModel) OR " +
            "(:filterClockSpeed IS NULL OR :filterClockSpeed = p.clockSpeed)")
    List<CPU> findByFilter(@Param("filterManufacturer") String filterManufacturer,
                           @Param("filterSocket") String filterSocket,
                           @Param("filterCore") Integer filterCore,
                           @Param("filterArchitecture") String filterArchitecture,
                           @Param("filterCpuModel") String filterCpuModel,
                           @Param("filterClockSpeed") Double filterClockSpeed);

    @Query("SELECT p FROM CPU p WHERE " +
            "(:filterMinPrice IS NULL OR p.price >= :filterMinPrice) AND " +
            "(:filterMaxPrice IS NULL OR p.price <= :filterMaxPrice)")
    List<CPU> findByPrice(@Param("filterMinPrice") Double filterMinPrice,
                          @Param("filterMaxPrice") Double filterMaxPrice);
}
