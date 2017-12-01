package main.repositories;

import main.models.UserType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository extends CrudRepository<UserType, Integer> {
	List<UserType> findAll();
}
