package main.repositories;

import main.models.CustomerType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerTypeRepository extends CrudRepository<CustomerType, Integer> {
	List<CustomerType> findAll ();
}
