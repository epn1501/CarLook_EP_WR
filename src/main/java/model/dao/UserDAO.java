package model.dao;

import model.objects.dto.User;
import process.control.LoginControl;
import process.control.exceptions.DatabaseException;
import services.db.JDBCConnection;
import services.util.Roles;
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

            throw new DatabaseException(EXCEPTION);
        } finally {
            model.dao.AbstractDAO.closeResultset(rs);
            JDBCConnection.getInstance().closeConnection();
        }
        return null;
    }

    public boolean createUser(User user)  {

        String sql = "INSERT INTO carlookwr.user (login, password, vorname, nachname, userid) VALUES (?, ?, ?, ?, default)";
        PreparedStatement stm = this.getPreparedStatement(sql);
        ResultSet rs = null;

        try{
            stm.setString(1, user.getLogin());
            stm.setString(2, user.getPasswort());
            stm.setString(3, user.getVorname());
            stm.setString(4, user.getNachname());
            int rowsChanged = stm.executeUpdate();
            if(rowsChanged == 0){
                throw  new SQLException("Creating user failed");
            }
            rs = stm.getGeneratedKeys();
            return true;

        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
            return false;
        } finally {
            closeResultset(rs);
        }
    }
}