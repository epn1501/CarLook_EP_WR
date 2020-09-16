package process.control;

import com.vaadin.ui.UI;
import gui.ui.MyUI;
import model.dao.UserDAO;
import model.objects.dto.User;
import process.control.exceptions.DatabaseException;
import java.sql.SQLException;

public class RegisterControl {

    User user = ((MyUI) UI.getCurrent()).getUser();
    /*
    public boolean checkUserExists(String login) throws DatabaseException{
        return UserDAO.getInstance().checkUserExists(login);
    } */

    public static boolean registerUser(User user) throws DatabaseException, SQLException{
        boolean result = UserDAO.getInstance().createUser(user);
        return result;
    }
}