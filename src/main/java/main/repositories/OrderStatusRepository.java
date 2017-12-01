package main.repositories;

import main.models.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus, Integer> {
	List<OrderStatus> findAll ();
}
