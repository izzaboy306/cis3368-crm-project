package main.services;

import main.models.User;
import org.springframework.stereotype.Service;

@Service
public class ActiveUserService {
	private User activeUser;

	public User getActiveUser () {
		return activeUser;
	}

	public void setActiveUser (User activeUser) {
		this.activeUser = activeUser;
	}
}
