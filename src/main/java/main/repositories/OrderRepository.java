package main.repositories;

import main.models.Order;
import main.models.OrderStatus;
import main.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	List<Order> findAll ();

	List<Order> findByOrderStatus (OrderStatus orderStatus);

	List<Order> findByOrderStatusAndUser(OrderStatus orderStatus, User user);

	@Transactional
	void removeByOrderId (int orderId);
}
