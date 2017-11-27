package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {

	private Parent root;
	private ConfigurableApplicationContext springContext;

	public static void main(String[] args) {
		launch();
	}

	/**
	 * The application initialization method. This method is called immediately
	 * after the Application class is loaded and constructed. An application may
	 * override this method to perform initialization prior to the actual starting
	 * of the application.
	 * <p>
	 * <p>
	 * The implementation of this method provided by the Application class does nothing.
	 * </p>
	 * <p>
	 * <p>
	 * NOTE: This method is not called on the JavaFX Application Thread. An
	 * application must not construct a Scene or a Stage in this
	 * method.
	 * An application may construct other JavaFX objects in this method.
	 * </p>
	 */
	@Override
	public void init() throws Exception {
		// get the springcontext
		springContext = SpringApplication.run(Main.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));

		// this tells JavaFX to use the spring factory to create the controller(s)
		fxmlLoader.setControllerFactory(springContext::getBean);
		root = fxmlLoader.load();
		super.init();
	}

	/**
	 * The main entry point for all JavaFX applications.
	 * The start method is called after the init method has returned,
	 * and after the system is ready for the application to begin running.
	 * <p>
	 * <p>
	 * NOTE: This method is called on the JavaFX Application Thread.
	 * </p>
	 *
	 * @param primaryStage the primary stage for this application, onto which
	 *                     the application scene can be set. The primary stage will be embedded in
	 *                     the browser if the application was launched as an applet.
	 *                     Applications may create other stages, if needed, but they will not be
	 *                     primary stages and will not be embedded in the browser.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Demo of Spring JPA");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}