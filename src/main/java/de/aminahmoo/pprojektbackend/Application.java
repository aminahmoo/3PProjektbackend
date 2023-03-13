package de.aminahmoo.pprojektbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private final Application instance;

	public Application() {
		instance = this;

		//init here
	}

	public static void main(String[] args) {
		Application application = new Application();
		application.startApplication(args);
	}

	private void startApplication(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public Application getInstance() {
		return instance;
	}
}
