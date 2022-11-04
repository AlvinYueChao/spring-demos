package com.alvin;

import com.alvin.mybatis.spring.MyBatisMapperScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.alvin")
@MyBatisMapperScan("com.alvin.mapper")
public class AppConfig {

}
