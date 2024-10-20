package be.thomasmore.pcbuilder.Repos;

import be.thomasmore.pcbuilder.Models.Build;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildRepository extends JpaRepository<Build, Long> {
    List<Build> findByUserId(Long userId);
}
