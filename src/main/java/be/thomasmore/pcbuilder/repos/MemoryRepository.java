package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.*;
import org.springframework.data.repository.CrudRepository;

public interface MemoryRepository extends CrudRepository<RAM, Integer> {
}
