package org.example.alvin.springexamples.annotation.aop.transaction.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceB {

  private final Logger logger = LogManager.getLogger(ServiceB.class);

  private final DataSource dataSource;

  @Autowired
  public ServiceB(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Transactional
  public void doSomethingOneForB() throws SQLException {
    /*logger.info("Start inserting record into tableB, current dataSource: {}", this.dataSource);
    Connection connection = DataSourceUtils.getConnection(dataSource);
    if (connection.getAutoCommit()) {
      connection.setAutoCommit(false);
    }
    String insertQuery = "INSERT INTO tableb (id, name) VALUES (?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
    preparedStatement.setInt(1, 1);
    preparedStatement.setString(2, "Alvin");
    int i = preparedStatement.executeUpdate();
    throw new RuntimeException("manual error occurs");*/

    try {
      logger.info("Start inserting record into tableB, current dataSource: {}", this.dataSource);
      Connection connection = DataSourceUtils.getConnection(dataSource);
      if (connection.getAutoCommit()) {
        connection.setAutoCommit(false);
      }
      String insertQuery = "INSERT INTO tableb (id, name) VALUES (?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
      preparedStatement.setInt(1, 1);
      preparedStatement.setString(2, "Alvin");
      preparedStatement.executeUpdate();
      throw new RuntimeException("manual error occurs");
    } catch (RuntimeException e) {
      logger.warn("cached runtime exception", e);
    }
  }
}
