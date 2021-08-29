package org.example.alvin.springexamples.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;

public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

  private static final Logger LOGGER = LogManager.getLogger(MyApplicationListener.class);

  @Override
  public void onApplicationEvent(MyApplicationEvent event) {
    LOGGER.info("Received application event: {}", event.getSource());
  }
}
