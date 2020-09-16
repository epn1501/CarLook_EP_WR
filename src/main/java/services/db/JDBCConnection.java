package services.db;

import org.postgresql.Driver;
import process.control.exceptions.DatabaseException;
import services.util.Password;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {

    private static JDBCConnection connection = null;
    private String url = "jdbc:postgresql://dumbo.inf.h-brs.de:5432/wrast2s";
    private String login = "wrast2s";
    private String password = Password.getPassword();
    private Connection conn;

    private JDBCConnection() throws DatabaseException {
        this.initConnection();
    }

    public  static JDBCConnection getInstance() throws DatabaseException {
        if(connection == null){
            connection = new JDBCConnection();
        }
        return connection;
    }


    public void initConnection() throws DatabaseException {
        try {
            DriverManager.registerDriver(new Driver());

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.openConnection();
    }

    public void openConnection() throws DatabaseException {

        try {
        Properties properties = new Properties();
        properties.setProperty("user", "wrast2s");
        properties.setProperty("password", Password.PASSWORD);

            this.conn = DriverManager.getConnection(this.url, properties);

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler beim Zugriff auf die Datenbank! Sichere Verbindung vorhanden?");
        }
    }

        public Statement getStatement() {
        try {
            if(this.conn.isClosed()){
                this.openConnection();
            }
            return this.conn.createStatement();
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            }
        }

        public void closeConnection() {
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public PreparedStatement getPreparedStatement(String sql) throws DatabaseException {
            try {
                if (this.conn.isClosed()) {
                    this.openConnection();
                }
                return this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException throwables) {
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
                throw new DatabaseException("Fehler beim Zugriff auf die Datenbank! Sichere Verbindung vorhanden?");
            }
        }
}