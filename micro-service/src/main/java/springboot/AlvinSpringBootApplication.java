package springboot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springboot.autoconfigure.AlvinImportSelector;
import springboot.web.WebServerAutoConfiguration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@ComponentScan
@Import({WebServerAutoConfiguration.class, AlvinImportSelector.class})
public @interface AlvinSpringBootApplication {

}
