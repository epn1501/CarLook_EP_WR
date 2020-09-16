package model.dao;

import process.control.exceptions.DatabaseException;
import services.db.JDBCConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO {

    protected Statement getStatement(){

        Statement statement = null;
        try{
            statement = JDBCConnection.getInstance().getStatement();
        } catch (DatabaseException ex) {
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statement;
    }

    protected PreparedStatement getPreparedStatement(String sql){

        PreparedStatement statement = null;
        try{
            statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        } catch (DatabaseException ex) {
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statement;
    }

    protected static void closeResultset(ResultSet r) {
        if (r != null) {
            try {
                r.close();
            } catch (SQLException throwables) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, throwables.getMessage());
            }
        }
    }
}