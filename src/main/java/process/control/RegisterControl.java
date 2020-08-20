package process.control;

import com.vaadin.ui.UI;
import gui.ui.MyUI;
import model.dao.UserDAO;
import model.objects.dto.User;
import process.control.exceptions.DatabaseException;
import services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterControl {

    User user = ((MyUI) UI.getCurrent()).getUser();


    /*
    public boolean chechUserExists(String login) throws DatabaseException{
        return UserDAO.getInstance().checkUserExists(login);
    }
    
     */

    /*
    public static boolean registerUser(String login, String passwort, String vorname, String nachname) throws DatabaseException, SQLException {

         boolean result = UserDAO.getInstance().createUser(login, passwort, vorname, nachname);
         return result;
    }
    */



}
