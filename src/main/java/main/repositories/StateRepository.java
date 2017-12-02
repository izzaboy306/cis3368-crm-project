package main.repositories;

import main.models.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends CrudRepository<State, Integer> {
	List<State> findAll ();
}
