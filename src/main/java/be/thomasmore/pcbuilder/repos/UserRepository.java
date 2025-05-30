package be.thomasmore.pcbuilder.repos;

import org.springframework.data.repository.CrudRepository;

import be.thomasmore.pcbuilder.models.User;
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
