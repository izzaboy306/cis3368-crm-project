package main.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import main.view.FxmlView;
import main.view.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ManagerMainController implements FxmlController {
	private final StageManager stageManager;

	@Autowired
	@Lazy
	public ManagerMainController (StageManager stageManager) {
		this.stageManager = stageManager;
	}

	/**
	 * Called by the {@link FXMLLoader} to initialize a controller after its
	 * root element has been completely processed. This means all of the
	 * controller's {@link FXML} elements will be injected, and they can be used
	 * to wire up the GUI in ways that couldn't be accomplished using pure FXML,
	 * e.g. attaching property listeners.
	 */
	@Override
	public void initialize () {

	}

	public void goBack() {
		stageManager.switchScene(FxmlView.MAIN);
	}
	public void loadSalesScreen() {
		stageManager.switchScene(FxmlView.SALESMAIN);
	}
}