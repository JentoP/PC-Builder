package be.thomasmore.pcbuilder.Repos;

import be.thomasmore.pcbuilder.models.CPU;
import org.springframework.data.repository.CrudRepository;

public interface ComponentRepository extends CrudRepository<CPU, Integer> {


}
