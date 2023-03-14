package de.aminahmoo.pprojektbackend.database;

import de.aminahmoo.pprojektbackend.Application;
import de.aminahmoo.pprojektbackend.database.DatabaseHandler.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Arrays;

public class MitarbeiterHandler {

    private final Logger logger;

    public MitarbeiterHandler() {
        logger = LoggerFactory.getLogger(MitarbeiterHandler.class);
    }

    public void addMitarbeiter(String name) {
        try(Connection conn = Application.getInstance().getDatabase().getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO test_table VALUES (?, ?)")) {
            ps.setInt(1, getNextFreeId());
            ps.setString(2, name);
            ps.executeUpdate();
        }catch(SQLException e) {
            logger.error("cant add a new employee", e);
        }
    }

    private int getNextFreeId() {
        try(Connection conn = Application.getInstance().getDatabase().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT MAX(id) FROM test_table")) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                logger.debug("HIGHEST ID: ", rs.getInt("id"));
                return rs.getInt("id")+1;
            }
        }catch(SQLException e) {
            logger.error("error on getnextfreeid", e);
        }
        return -1;
    }

    public String getResultSet() {
        try(Connection conn = Application.getInstance().getDatabase().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM test_table")) {

            ResultSet resultSet = ps.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            StringBuilder stringBuilder = new StringBuilder();

            while(resultSet.next()) {
                Object[] values = new Object[resultSetMetaData.getColumnCount()];
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    values[i - 1] = resultSet.getObject(i);
                }
                stringBuilder.append(Arrays.toString(values));
            }

            if(stringBuilder.isEmpty()) {
                stringBuilder.append("Nothing found!");
            }

            return stringBuilder.toString();
        }catch(SQLException e) {
            logger.error("error on getting all \"Mitarbeiter\"", e);
        }
        return "";
    }

}
