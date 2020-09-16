package model.dao;

import model.objects.dto.Role;
import model.objects.dto.User;
import process.control.LoginControl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDAO extends AbstractDAO{

    //SINGLETON
    public static RoleDAO dao = null;

    private RoleDAO(){
    }

    public static RoleDAO getInstance() {
        if(dao == null){
            dao = new RoleDAO();
        }
        return dao;
    }

    public List<Role> getRolesForUser (User user){

        Statement statement = this.getStatement();

        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT * " +
                    "FROM carlookwr.user_to_rolle " +
                    "WHERE carlookwr.user_to_rolle.login = \'" + user.getLogin() + "\'" );

        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if( rs == null) return null;

        List<Role> liste = new ArrayList<Role>();
        Role role = null;

        try {
            while (rs.next()) {
                role = new Role();
                //liest 2 Spalte aus -> Rolle
                role.setBezeichnung(rs.getString(2));
                liste.add(role);
            }
        } catch(SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    public boolean createRolle (User user, Role rolle){

        String sql = "INSERT INTO carlookwr.user_to_rolle (login, rolle) VALUES (?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);
        ResultSet rs = null;

        try {

            statement.setString(1, user.getLogin());
            statement.setString(2, rolle.getBezeichnung());
            int rowsChanged = statement.executeUpdate();
            if(rowsChanged == 0){
                throw new SQLException("Creating Role to User failed");
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
}