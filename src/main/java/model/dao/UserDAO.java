package model.dao;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import gui.ui.MyUI;
import model.objects.dto.User;
import process.control.LoginControl;
import process.control.exceptions.DatabaseException;
import services.db.JDBCConnection;
import services.util.CustomerService;
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
    private String role = Roles.CURRENT_USER;

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

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
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
                    "FROM carlookwr.user "
                    + "WHERE carlookwr.user.login = ?");
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


            String query = " insert into carlookwr.user (login, password, vorname, nachname) values (?,?,?,?, default)";
            PreparedStatement statement = this.getPreparedStatement(query);

            statement.setString(1, getLogin());
            statement.setString(2, getPassword());
            statement.setString(3, getVorname());
            statement.setString(4, getNachname());

            statement.execute();

            return true;


    }
    */


    public void addUser(User user) throws SQLException, DatabaseException {

        CustomerService service = new CustomerService();
        //service.updateUser(user);

        System.out.println("User Login: " + user.getLogin()+ " User Passwort:  " + user.getPasswort());

    }




}
