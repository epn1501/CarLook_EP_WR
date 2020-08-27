package model.dao;

import model.objects.dto.Auto;
import process.control.exceptions.DatabaseException;
import services.db.JDBCConnection;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoDAO extends AbstractDAO{

    private static AutoDAO dao = null;

    public AutoDAO(){

    }

    public static AutoDAO getInstance(){
        if(dao == null){
            dao = new AutoDAO();
        }

        return dao;
    }


    public List<Auto> getAutoByBrand (String marke){

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
            rs = statement.executeQuery("SELECT *"
                    + "FROM carlookwr.autos "
                    + "WHERE carlookwr.autos.marke = \'" + marke + "\'");
        }catch (SQLException ex){

        }

        if( rs == null ){
            return null;
        }

        List<Auto> liste = new ArrayList<Auto>();
        Auto auto = null;

        try{
            while (rs.next()){
                auto = new Auto();
                auto.setId(rs.getInt(1));
                auto.setMarke(rs.getString(2));
                auto.setPs(rs.getInt(3));
                auto.setBaujahr(rs.getInt(4));
                auto.setDescription(rs.getString(5));
                liste.add(auto);
            }
        }catch (SQLException ex){

        }
        return liste;


    }

    public List<Auto>  getAutoByAll(){

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
            rs = statement.executeQuery("SELECT *"
                    + "FROM carlookwr.autos ");
        }catch (SQLException ex){

        }

        if( rs == null ){
            return null;
        }

        List<Auto> liste = new ArrayList<Auto>();
        Auto auto = null;

        try{
            while (rs.next()){
                auto = new Auto();
                auto.setId(rs.getInt(1));
                auto.setMarke(rs.getString(2));
                auto.setPs(rs.getInt(3));
                auto.setBaujahr(rs.getInt(4));
                auto.setDescription(rs.getString(5));
                liste.add(auto);
            }
        }catch (SQLException ex){

        }
        return liste;


    }



    public boolean createAuto(Auto auto){

        String sql = "INSERT INTO carlookwr.autos (auto_id, marke, ps, baujahr, description) VALUES (default, ?, ?, ?, ?)";
        PreparedStatement statement = this.getPreparedStatement(sql);
        ResultSet rs = null;

        try{
            statement.setString(1, auto.getMarke());
            statement.setInt(2, auto.getPs());
            statement.setInt(3, auto.getBaujahr());
            statement.setString(4, auto.getDescription());
            int rowsChanged = statement.executeUpdate();
            if(rowsChanged == 0){
                throw new SQLException("Creating auto failed");
            }
            rs = statement.getGeneratedKeys();
            return true;
        }catch (SQLException ex){
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
            return false;
        }finally {
            closeResultset(rs);
        }

    }


}
