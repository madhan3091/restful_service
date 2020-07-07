package generic.utils;

import generic.config.ConfigPropertyReader;

import java.sql.*;
import java.util.Properties;

public interface database {

    public Properties obj = ConfigPropertyReader.readConfig("config.properties");

    String url = obj.getProperty("dbUrl");
    String driverName = obj.getProperty("dbDriverName");
    String username = obj.getProperty("dbUsername");
    String password = obj.getProperty("dbPassword");

    Connection getConnection() throws SQLException;
    ResultSet getResultSet(String query) throws SQLException;
    void closeConnection() throws SQLException;
}
