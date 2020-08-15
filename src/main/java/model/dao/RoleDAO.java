package model.dao;

import model.objects.dto.Role;
import model.objects.dto.User;
import process.control.LoginControl;
import process.control.exceptions.DatabaseException;
import services.db.JDBCConnection;

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

        /*
        Statement statement = null;
        try{
            statement = JDBCConnection.getInstance().getStatement();
        } catch (DatabaseException ex){
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        */

        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT * " +
                    "FROM carlookew.user_to_rolle " +
                    "WHERE carlookew.user_to_rolle.login = \'" + user.getLogin() + "\'" );

        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if( rs == null) {
            return null;
        }

        List<Role> liste = new ArrayList<Role>();
        Role role = null;

        try {
            while (rs.next()) {
                role = new Role();
                //liest 2 Spalte aus
                role.setBezeichnung(rs.getString(2));
                liste.add(role);
            }
        }catch(SQLException ex){
                Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return liste;
        }


    }


