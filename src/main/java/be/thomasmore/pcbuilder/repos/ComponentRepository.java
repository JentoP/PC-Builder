package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.CPU;
import org.springframework.data.repository.CrudRepository;

public interface ComponentRepository extends CrudRepository<CPU, Integer> {


}
