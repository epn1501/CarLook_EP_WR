package model.dao;

import com.vaadin.server.VaadinSession;
import model.objects.dto.User;
import process.control.LoginControl;
import process.control.exceptions.DatabaseException;
import services.db.JDBCConnection;

import java.net.PasswordAuthentication;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends AbstractDAO{

    private static final String EXCEPTION = "Fehler im SQL-Befehl! Bitte den Programmierer benachrichtigen!";
    private static UserDAO dao = null;



    private UserDAO(){

    }

    public static UserDAO getInstance(){
        if(dao == null){
            dao = new UserDAO();
        }
        return dao;
    }

    public static User getUser(String login) throws DatabaseException{

        ResultSet rs = null;
        User user = null;

        try{
            PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement("SELECT * " +
                    "FROM carlookew.user "
                    + "WHERE carlook.user.login = ?");
            statement.setString(1, login);
            rs = statement.executeQuery();

            while(rs.next()){
                user = new User();
                user.setLogin(rs.getString(1));
                user.setPasswort(rs.getString(2));
                user.setVorname(rs.getString(3));
                user.setNachname(rs.getString(4));
            }

        }catch (SQLException | DatabaseException e) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, e.getMessage());

            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        } finally {
            model.dao.AbstractDAO.closeResultset(rs);
            JDBCConnection.getInstance().closeConnection();
        }

        return null;
    }



    public boolean createUser(String login, String passwort, String vorname, String nachname) throws SQLException {

        String sql = "INSERT INTO carlookew.user(login, password, vorname, nachname) VALUES (?,?,?,?,default)";
        PreparedStatement statement = this.getPreparedStatement(sql);
        //PasswordAuthentication hasher = new PasswordAuthentication();

        char[] c = passwort.toCharArray();
        ResultSet genKeys = null;

        try{
            statement.setString(1, login);
            statement.setString(2, passwort);
            statement.setString(3, vorname);
            statement.setString(4, nachname);

            int rowsChanged = statement.executeUpdate();
            if(rowsChanged == 0){
                throw new SQLException("Creating new User failed!");
            }
            genKeys = statement.getGeneratedKeys();
            if(genKeys.next()){
                Long userId = genKeys.getLong(1);
                VaadinSession.getCurrent().setAttribute("userId", userId);
               // logEntry("UserDAO", Level.INFO, "Found UserID: " + userId);
            }else{
                throw new SQLException("Creating User failed! No ID obtained!");
            }

        }catch (SQLException ex) {
            //logEntry(this.getClass().getName(), Level.SEVERE, ex.getMessage());
            //return false;
        } finally {
            closeResultset(genKeys);
        }
        return false;
    }




}


