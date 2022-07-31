package dubbo.provider.anno.validation;

import dubbo.api.validation.Validation;
import dubbo.api.validation.ValidationParameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService(validation = "true")
public class ValidationImpl implements Validation {

  @Override
  public String checkBeforeSomething(ValidationParameter validationParameter) {
    log.info("validation completed, do something...");
    return validationParameter == null ? null : validationParameter.toString();
  }
}
