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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceA {

  private final Logger logger = LogManager.getLogger(ServiceA.class);

  private final DataSource dataSource;

  @Autowired
  public ServiceA(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Transactional
  public void doSomethingOneForA() throws SQLException {
    logger.info("Start inserting record into tableA, current dataSource: {}", this.dataSource);
    Connection connection = DataSourceUtils.getConnection(dataSource);
    logger.info("connection in method with @Transaction: {}", connection);
    String insertQuery = "INSERT INTO tablea (id, name) VALUES (?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
    preparedStatement.setInt(1, 1);
    preparedStatement.setString(2, "Iphone SE");
    int i = preparedStatement.executeUpdate();
  }
}
