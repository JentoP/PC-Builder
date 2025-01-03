package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.PcBuild;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcBuildRepository extends CrudRepository<PcBuild, Integer> {
    // Find builds by user
    // List<PcBuild> findByUser(User user);

    // Or find a specific build by its name
    // Optional<PcBuild> findByBuildName(String buildName);
}
