package dubbo.provider.anno;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.PropertySource;

@EnableDubbo(scanBasePackages = "dubbo.provider.anno")
@PropertySource("dubbo.properties")
public class AnnoBean {
}
