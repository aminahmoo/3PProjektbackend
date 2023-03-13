package de.aminahmoo.pprojektbackend.database;

import de.aminahmoo.pprojektbackend.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Arrays;

public class MitarbeiterHandler {

    private final Logger logger;

    public MitarbeiterHandler() {
        logger = LoggerFactory.getLogger(MitarbeiterHandler.class);
    }


    public String getResultSet() {
        try(Connection conn = Application.getInstance().getDatabase().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Mitarbeiter")) {

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
