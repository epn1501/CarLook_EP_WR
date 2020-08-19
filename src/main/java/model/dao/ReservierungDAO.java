package model.dao;

import entities.Reservierung;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservierungDAO extends AbstractDAO {

    public static ReservierungDAO dao = null;

    private ReservierungDAO() {


    }

    public static ReservierungDAO getInstance() {
        if (dao == null) {
            dao = new ReservierungDAO();
        }
        return dao;
    }

    public boolean addReservierung(Reservierung reservierung) {
        String sql = "insert into carlookew.reservierung values (default, ?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);

        //User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        //String userLogin = user.getLogin();

        try {
            statement.setString(1, reservierung.getUser().getLogin());
            statement.setInt(2, reservierung.getAuto().getId());

            statement.executeUpdate();

            setReservierungsID(reservierung);

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ReservierungDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /*
    public void getAllReservierungForUser(User user) {
        Statement statement = this.getStatement();
        return statement;
        return 0;
    }
    */

    private void setReservierungsID(Reservierung reservierung){
        Statement statement = this.getStatement();

        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT max(carlookew.reservierung.id)" +
                    "FROM carlookew.reservierung");
        }catch(SQLException ex){
            Logger.getLogger(ReservierungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        int currentValue = 0;
        try{
            rs.next();
            currentValue = rs.getInt(1);
        }catch (SQLException ex){
            Logger.getLogger(ReservierungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        reservierung.setId(currentValue);

    }

    public void deleteReservierungBy (int id){
        Statement statement = this.getStatement();

        try{
            statement.execute("DELETE FROM carlookew.reservierung WHERE carlookew.reservierung.id = \'" + id + "\';");
        }catch(SQLException ex){

        }
    }

}