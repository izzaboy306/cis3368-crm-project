package main.repositories;

import main.models.User;
import main.models.UserType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	List<User> findAll ();

	List<User> findAllByUserTypeEquals (UserType userType);
}