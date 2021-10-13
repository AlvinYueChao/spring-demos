package org.example.alvin.springexamples.annotation.aop.transaction;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Component
public class TransactionManagementConfigurerBean implements TransactionManagementConfigurer {

  private final DataSource dataSource;

  @Autowired
  public TransactionManagementConfigurerBean(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public TransactionManager annotationDrivenTransactionManager() {
    DataSourceTransactionManager dtm = new DataSourceTransactionManager();
    dtm.setDataSource(dataSource);
    return dtm;
  }
}
