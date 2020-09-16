package process.control;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import model.objects.dto.User;
import process.control.exceptions.DatabaseException;
import process.control.exceptions.NoSuchUserOrPassword;
import services.db.JDBCConnection;
import services.util.Roles;
import services.util.Views;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginControl{

    public static void checkAuthentication(String login, String password ) throws NoSuchUserOrPassword, DatabaseException {

        ResultSet set = null;

        try {
            // DB-Zugriff
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT * " +
                    "FROM carlookwr.user " +
                    "WHERE carlookwr.user.login = \'" + login + "\'"
                    + "AND carlookwr.user.password = \'" + password + "\'");
        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Programmierer benachrichtigen!");
        }

        User user = null;

        try {
            if (set.next()) {

                user = new User();
                user.setLogin(set.getString(1));
                user.setVorname(set.getString(3));
            } else {
                //Fehlerfall
                throw new NoSuchUserOrPassword();
            }
        }catch(SQLException ex){
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }

        VaadinSession session = UI.getCurrent().getSession();
        session.setAttribute(Roles.CURRENT_USER, user);

        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
    }

    public static void logoutUser(){
        UI.getCurrent().getPage().setLocation("/CarLook_EP_WR");
        UI.getCurrent().getSession().close();
    }
}