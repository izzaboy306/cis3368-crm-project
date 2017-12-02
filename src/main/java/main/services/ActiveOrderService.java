package main.services;

import main.models.Order;
import org.springframework.stereotype.Service;

@Service
public class ActiveOrderService {
	private Order activeOrder;

	public Order getActiveOrder () {
		return activeOrder;
	}

	public void setActiveOrder (Order activeOrder) {
		this.activeOrder = activeOrder;
	}
}
