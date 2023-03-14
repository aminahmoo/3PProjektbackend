package de.aminahmoo.pprojektbackend;

import de.aminahmoo.pprojektbackend.database.DatabaseHandler.Database;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application {

	private static Application instance;
	private final Dotenv dotenv;
	private Database database;

	public Application() throws SQLException {
		instance = this;

		//init stuff here
		dotenv = Dotenv.load();
		database = new Database();
	}

	public static void main(String[] args) throws SQLException{
		Application application = new Application();
		application.startApplication(args);

	}

	private void startApplication(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public static Application getInstance() {
		return instance;
	}

	public Dotenv getDotenv() {
		return dotenv;
	}

	public Database getDatabase() {
		return database;
	}
}
