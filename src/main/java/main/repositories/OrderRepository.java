package main.repositories;

import main.models.Order;
import main.models.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	List<Order> findAll ();

	List<Order> findByOrderStatus (OrderStatus orderStatus);

	@Transactional
	void removeByOrderId (int orderId);
}
