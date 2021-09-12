package org.example.alvin.springexamples.xml.bean.applicationeventlistener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;

//@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

  private final Logger logger = LogManager.getLogger(MyApplicationListener.class);

  @Override
  public void onApplicationEvent(MyApplicationEvent event) {
    logger.info("Received application event: {}", event.getSource());
  }
}
