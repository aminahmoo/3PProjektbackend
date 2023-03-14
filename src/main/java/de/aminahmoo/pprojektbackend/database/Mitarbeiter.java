package de.aminahmoo.pprojektbackend.database;

import de.aminahmoo.pprojektbackend.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Mitarbeiter {

    private final int id;
    private final String name;

    public Mitarbeiter(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Mitarbeiter fromId(int id) {
        try(Connection conn = Application.getInstance().getDatabase().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM test_table WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Mitarbeiter(rs.getInt("id"), rs.getString("name"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Mitarbeiter> getAllMitarbeiter() {
        try(Connection conn = Application.getInstance().getDatabase().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM test_table")) {
            ResultSet rs = ps.executeQuery();
            List<Mitarbeiter> mitarbeiterList = new LinkedList<>();
            while(rs.next()) {
                mitarbeiterList.add(new Mitarbeiter(rs.getInt("id"), rs.getString("name")));
            }
            return mitarbeiterList;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
