package dubbo.provider.anno;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.PropertySource;

@EnableDubbo(scanBasePackages = "dubbo.provider.anno")
@PropertySource("dubbo.properties")
public class AnnoBean {
}
