package dubbo.consumer.anno;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.PropertySource;

@EnableDubbo(scanBasePackages = "dubbo.consumer.anno")
@PropertySource("dubbo.properties")
public class AnnoBean {
}
