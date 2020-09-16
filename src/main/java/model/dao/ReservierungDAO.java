package model.dao;

import model.objects.dto.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservierungDAO extends AbstractDAO {
    public static ReservierungDAO dao = null;

    private  ReservierungDAO(){
    }

    public static ReservierungDAO getInstance(){
        if(dao == null){
            dao = new ReservierungDAO();
        }
        return dao;
    }

    public boolean createReservierung (User user){

    String sql = "INSERT INTO carlookwr.reservierung (id, userid) VALUES (default, ?) ";
        PreparedStatement statement = this.getPreparedStatement(sql);
        ResultSet rs = null;

        try{
            statement.setString(1, user.getLogin());
            int rowsChanged = statement.executeUpdate();
            if(rowsChanged == 0){
                throw new SQLException("Creating Reservierung failed");
            }
            rs = statement.getGeneratedKeys();
            return true;
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
            return false;
        } finally {
            closeResultset(rs);
        }
    }

    public void getReservierungsId(User user) {

        Statement statement = this.getStatement();

        ResultSet rs = null;

        try {
          rs =  statement.executeQuery("SELECT *"
                    + "FROM carlookwr.reservierung ");

          //WHERE carlookwr.reservierung.userid = " + user.getLogin()

          while (rs.next()){
             int id = rs.getInt(1);

              System.out.println("Die ID lautet: " + id);
          }
          statement.close();

        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }
}