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
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class ServiceB {

  private final Logger logger = LogManager.getLogger(ServiceB.class);

  private final DataSource dataSource;

  @Autowired
  private TransactionTemplate transactionTemplate;

  @Autowired
  public ServiceB(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void doSomethingOneForB() throws SQLException {
    logger.info("Start inserting record into tableB, current dataSource: {}", this.dataSource);
    Connection connection = DataSourceUtils.getConnection(dataSource);
    logger.info("connection in method with @Transaction: {}", connection);

    transactionTemplate.execute(status -> {
      Connection connection1 = DataSourceUtils.getConnection(dataSource);
      logger.info("connection in programing transaction: {}", connection1);
      String insertQuery = "INSERT INTO tableb (id, name) VALUES (?, ?)";
      int affectedRows = 0;
      try {
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "Alvin");
        affectedRows = preparedStatement.executeUpdate();
        throw new RuntimeException("manual error occurs");
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return affectedRows;
    });

    /*try {
      logger.info("Start inserting record into tableB, current dataSource: {}", this.dataSource);
      Connection connection = DataSourceUtils.getConnection(dataSource);
      String insertQuery = "INSERT INTO tableb (id, name) VALUES (?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
      preparedStatement.setInt(1, 1);
      preparedStatement.setString(2, "Alvin");
      preparedStatement.executeUpdate();
      throw new RuntimeException("manual error occurs");
    } catch (RuntimeException e) {
      logger.warn("cached runtime exception", e);
    }*/
  }
}
