package services.util;

import model.objects.dto.Auto;
import model.objects.dto.NeueListeAuto;
import model.objects.dto.User;
import process.control.exceptions.DatabaseException;
import services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

public class CustomerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JDBCConnection connection;

    public List<Auto> findAll() {
        return jdbcTemplate.query("SELECT auto_id, marke, ps, baujahr, description FROM carlookwr.autos",
                (rs, rowNum) -> new Auto(rs.getInt("id"),
                        rs.getString("Marke"),
                        rs.getInt("ps"),
                        rs.getInt("baujahr"),
                        rs.getString("description")));
    }

    public void update(Auto auto) {
        jdbcTemplate.update("INSERT INTO carlookwr.autos (auto_id,marke,ps,baujahr,description) VALUES(?,?,?,?)"
                , auto.getId(), auto.getMarke(), auto.getPs(), auto.getBaujahr(), auto.getDescription());

    }
        /*
    public void updateUser(User user) {
        try {

            jdbcTemplate.update("INSERT INTO carlookwr.user (login, password, vorname, nachname) VALUES (?,?,?,?, default)",
                    user.getLogin(), user.getPasswort(), user.getVorname(), user.getNachname());
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }
        */

    /*
        public void updateUser(User user) {

            try {
                PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement("INSERT INTO carlookwr.user " +
                                "(login, password, vorname, nachname) VALUES (?,?,?,?, default)");
                statement.executeUpdate(user.getLogin());
                statement.executeUpdate(user.getPasswort());
                statement.executeUpdate(user.getVorname());
                statement.executeUpdate(user.getNachname());
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }

        }
    */




}