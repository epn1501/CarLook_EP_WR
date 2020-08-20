package model.dao;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import gui.ui.MyUI;
import model.objects.dto.User;
import process.control.LoginControl;
import process.control.exceptions.DatabaseException;
import services.db.JDBCConnection;
import services.util.Roles;

import java.net.PasswordAuthentication;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends AbstractDAO {

    private static final String EXCEPTION = "Fehler im SQL-Befehl! Bitte den Programmierer benachrichtigen!";
    private static UserDAO dao = null;

    private String login = null;
    private String password = null;
    private String vorname = null;
    private String nachname = null;
    private int number;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public UserDAO() {

    }

    public UserDAO(String login, String password, String vorname, String nachname) {
        super();
    }

    public static UserDAO getInstance() {
        if (dao == null) {
            dao = new UserDAO();
        }
        return dao;
    }

    public static User getUser(String login, String password, String vorname, String nachname) throws DatabaseException {

        ResultSet rs = null;
        User user = null;

        try {
            PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement("SELECT * " +
                    "FROM carlookew.user "
                    + "WHERE carlook.user.login = ?");
            statement.setString(1, login);
            rs = statement.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setLogin(rs.getString(1));
                user.setPasswort(rs.getString(2));
                user.setVorname(rs.getString(3));
                user.setNachname(rs.getString(4));
            }

        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, e.getMessage());

            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        } finally {
            model.dao.AbstractDAO.closeResultset(rs);
            JDBCConnection.getInstance().closeConnection();
        }

        return null;
    }

    /*
    public boolean createUser(String login, String passwort, String vorname, String nachname) throws SQLException {


            String query = " insert into carlookew.user (login, password, vorname, nachname) values (?,?,?,?, default)";
            PreparedStatement statement = this.getPreparedStatement(query);

            statement.setString(1, getLogin());
            statement.setString(2, getPassword());
            statement.setString(3, getVorname());
            statement.setString(4, getNachname());

            statement.execute();

            return true;


    }
    */

    /*
    public void addUser(User user) throws SQLException, DatabaseException {

        try {
            String sql = "INSERT INTO carlookew.user (login, password, vorname, nachname) VALUES (?, ?, ?, ?, default)";
            PreparedStatement statement = this.getPreparedStatement(sql);

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPasswort());
            statement.setString(3, user.getVorname());
            statement.setString(4, user.getNachname());

            statement.executeUpdate(sql);


        }catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());

        }


        System.out.println("User Login: " + user.getLogin()+ " User Passwort:  " + user.getPasswort());

    }
        */


    public long addUser(User auswahl) throws SQLException {

        String query = "INSERT INTO user (login, password,vorname, nachname)  VALUES (?,?,?,?, default)";

        long id = 0;

        try(PreparedStatement statement = this.getPreparedStatement(query)) {


                statement.setString(1, auswahl.getLogin());
                statement.setString(2, auswahl.getPasswort());
                statement.setString(3, auswahl.getVorname());
                statement.setString(4, auswahl.getNachname());

                int affectedRows = statement.executeUpdate();

                if(affectedRows > 0){
                    try(ResultSet rs = statement.getGeneratedKeys()){
                            if(rs.next()){
                                id = rs.getLong(1);
                            }


                    }catch (SQLException ex){
                        System.out.println(ex.getMessage());
                    }

                }


            } catch (Exception e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());

            }
                return id;

    }


}


