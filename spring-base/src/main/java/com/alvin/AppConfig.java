package com.alvin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.alvin")
@Slf4j
@EnableAspectJAutoProxy
public class AppConfig {

}
