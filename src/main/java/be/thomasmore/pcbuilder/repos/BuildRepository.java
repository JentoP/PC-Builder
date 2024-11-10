package be.thomasmore.pcbuilder.repos;

import be.thomasmore.pcbuilder.models.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuildRepository extends CrudRepository<CPU, Integer> {
}
