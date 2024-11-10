package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.Build;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuildRepository extends CrudRepository<Build, Long> {
    List<Build> findByUserId(Long userId);
}
