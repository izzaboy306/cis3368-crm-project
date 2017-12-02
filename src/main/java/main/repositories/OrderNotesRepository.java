package main.repositories;

import main.models.Order;
import main.models.OrderNote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderNotesRepository extends CrudRepository<OrderNote, Integer> {
	List<OrderNote> findAll ();

	List<OrderNote> findAllByOrder (Order order);
}
