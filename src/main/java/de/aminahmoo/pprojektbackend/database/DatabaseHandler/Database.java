package de.aminahmoo.pprojektbackend.database.DatabaseHandler;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.aminahmoo.pprojektbackend.Application;
import de.aminahmoo.pprojektbackend.database.MitarbeiterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Database {

    private final Logger logger;
    private HikariConfig hikariConfig;
    public HikariDataSource hikariDataSource;

    private final MitarbeiterHandler mitarbeiterHandler;


    public Database() throws SQLException {
        logger = LoggerFactory.getLogger(Database.class);
        mitarbeiterHandler = new MitarbeiterHandler();
        initHikari();
    }


    public void closeConnection() {
        try {
            if(!hikariDataSource.getConnection().isClosed()) {
                hikariDataSource.getConnection().close();
            }
        }catch(SQLException e) {
            logger.error("Error on close connection", e);
        }
    }


    private void initHikari() throws SQLException {
        hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://"+
                Application.getInstance().getDotenv().get("DB_HOSTNAME")
                +"/"+
                Application.getInstance().getDotenv().get("DB_DATABASE"));
        hikariConfig.setUsername(Application.getInstance().getDotenv().get("DB_USERNAME"));
        hikariConfig.setPassword(Application.getInstance().getDotenv().get("DB_PASSWORD"));
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariDataSource = new HikariDataSource(hikariConfig);

        testDataSource();
        initDatabaseEntries();
    }


    /* for SQLExpress:
    private void initHikari() throws SQLException {
        hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:sqlserver://"+
                Application.getInstance().getDotenv().get("DB_HOSTNAME")+";"+
                "encrypt=true;"+
                "databaseName="+Application.getInstance().getDotenv().get("DB_DATABASE")+";"+
                "user="+Application.getInstance().getDotenv().get("DB_USERNAME")+";" +
                "password="+Application.getInstance().getDotenv().get("DB_PASSWORD")+";"+
                "integratedSecurity=true;" +
                "trustServerCertificate=true;");
        hikariConfig.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariDataSource = new HikariDataSource(hikariConfig);

        testDataSource();
        initDatabaseEntries();
    }
     */
    public void initDatabaseEntries() {
        try (Connection conn = hikariDataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS test_table (id INT, " +
                     "name VARCHAR(20), PRIMARY KEY(id))")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not create table: test_table", e);
        }
    }

    private void testDataSource() throws SQLException {

        try (Connection conn = hikariDataSource.getConnection()) {
            if (!conn.isValid(1000)) {
                throw new SQLException("Could not establish database connection.");
            }
            logger.info("Successfully connected to the database!");
        }
    }

    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    public MitarbeiterHandler getMitarbeiterHandler() {
        return mitarbeiterHandler;
    }
}