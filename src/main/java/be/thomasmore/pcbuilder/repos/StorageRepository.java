package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.*;
import org.springframework.data.repository.CrudRepository;

public interface StorageRepository extends CrudRepository<DATA, Integer> {
}
