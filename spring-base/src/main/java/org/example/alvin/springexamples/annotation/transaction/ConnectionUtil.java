package org.example.alvin.springexamples.annotation.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionUtil {

  private final static Logger logger = LogManager.getLogger(ConnectionUtil.class);

  private final static String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
  private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/test";
  private final static String DB_USERNAME = "root";
  private final static String DB_PASSWORD = "ZiF6OZ5eB4gW2kN0";

  public static Connection getConnection() throws ClassNotFoundException, SQLException {
    Connection connection = null;
    Class.forName(DB_DRIVER_CLASS);
    connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    return connection;
  }
}
