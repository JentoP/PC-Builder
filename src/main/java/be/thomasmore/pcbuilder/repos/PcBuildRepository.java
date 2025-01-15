package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.PcBuild;
import be.thomasmore.pcbuilder.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PcBuildRepository extends CrudRepository<PcBuild, Integer> {
    // Find builds by user
    Optional<PcBuild> findByIdAndUser(Integer id, User user);

    List<PcBuild> findAllByUser(User user);

    @Query("SELECT b FROM PcBuild b WHERE b.selectedCPU.id = :componentId")
    List<PcBuild> findBuildsByCPU(@Param("componentId") Integer componentId);

    @Query("SELECT b FROM PcBuild b WHERE b.selectedMOBO.id = :componentId")
    List<PcBuild> findBuildsByMOBO(@Param("componentId") Integer componentId);

    @Query("SELECT b FROM PcBuild b WHERE b.selectedMemory.id = :componentId")
    List<PcBuild> findBuildsByMemory(@Param("componentId") Integer componentId);

    @Query("SELECT b FROM PcBuild b WHERE b.selectedStorage.id = :componentId")
    List<PcBuild> findBuildsByStorage(@Param("componentId") Integer componentId);

    @Query("SELECT b FROM PcBuild b WHERE b.selectedGPU.id = :componentId")
    List<PcBuild> findBuildsByGPU(@Param("componentId") Integer componentId);

    @Query("SELECT b FROM PcBuild b WHERE b.selectedCase.id = :componentId")
    List<PcBuild> findBuildsByCase(@Param("componentId") Integer componentId);

    @Query("SELECT b FROM PcBuild b WHERE b.selectedCooler.id = :componentId")
    List<PcBuild> findBuildsByCooler(@Param("componentId") Integer componentId);

    @Query("SELECT b FROM PcBuild b WHERE b.selectedPowerSupply.id = :componentId")
    List<PcBuild> findBuildsByPowerSupply(@Param("componentId") Integer componentId);
}