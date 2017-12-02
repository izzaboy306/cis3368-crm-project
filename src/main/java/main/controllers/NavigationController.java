package main.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import main.view.FxmlView;
import main.view.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class NavigationController implements FxmlController {
	private final StageManager stageManager;

	@FXML private MenuItem homeMenuItem;
	@FXML private MenuItem logoutMenuItem;
	@FXML private MenuItem exitMenuItem;

	@Autowired
	@Lazy
	public NavigationController (StageManager stageManager) {
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
		homeMenuItem.setOnAction(event -> stageManager.switchScene(FxmlView.SALESPIPELINE));
		logoutMenuItem.setOnAction(event -> stageManager.switchScene(FxmlView.MAIN));
		exitMenuItem.setOnAction(event -> Platform.exit());
	}
}
