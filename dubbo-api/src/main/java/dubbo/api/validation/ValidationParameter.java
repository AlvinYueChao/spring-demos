package dubbo.api.validation;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * Validation 已知问题: 依赖版本过低，不支持 LocalDate
 */
public class ValidationParameter implements Serializable {

  @NotNull
  @Size(min = 2, max = 10)
  private String name;

  @Min(1)
  @Max(100)
  private Integer age;

  @Past
  private LocalDate loginDate;

  @Future
  private LocalDate expiryDate;
}
