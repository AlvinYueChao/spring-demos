package dubbo.provider.anno.validation;

import com.alibaba.dubbo.config.annotation.Service;
import dubbo.api.validation.Validation;
import dubbo.api.validation.ValidationParameter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(validation = "true")
public class ValidationImpl implements Validation {

  @Override
  public String checkBeforeSomething(ValidationParameter validationParameter) {
    log.info("validation completed, do something...");
    return validationParameter == null ? null : validationParameter.toString();
  }
}
