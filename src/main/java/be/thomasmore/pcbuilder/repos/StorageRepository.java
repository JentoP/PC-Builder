package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.DATA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StorageRepository extends CrudRepository<DATA, Integer> {

    @Query("select d from DATA d WHERE " +
            ":searchWord IS NULL OR " +
            "(d.name ILIKE CONCAT('%', :searchWord, '%')) OR" +
            "(d.manufacturer ILIKE CONCAT('%', :searchWord, '%'))")
    List<DATA> findBySearch(
            @Param("searchWord") String searchWord);

    @Query("SELECT d FROM DATA d WHERE " +
            "(:filterManufacturer IS NULL OR :filterManufacturer = d.manufacturer) AND " +
            "(:filterInterfaceType IS NULL OR :filterInterfaceType = d.interfaceType) AND " +
            "(:filterStorageType IS NULL OR :filterStorageType = d.storageType) AND " +
            "(:filterCapacity IS NULL OR :filterCapacity = d.capacity)")
    List<DATA> findByFilter(
            @Param("filterManufacturer") String filterManufacturer,
            @Param("filterInterfaceType") String filterInterfaceType,
            @Param("filterStorageType") String filterStorageType,
            @Param("filterCapacity") String filterCapacity);

    @Query("SELECT d FROM DATA d WHERE " +
            "(:filterMinPrice IS NULL OR d.price >= :filterMinPrice) AND " +
            "(:filterMaxPrice IS NULL OR d.price <= :filterMaxPrice)")
    List<DATA> findByPrice(
            @Param("filterMinPrice") Double filterMinPrice,
            @Param("filterMaxPrice") Double filterMaxPrice);
}
