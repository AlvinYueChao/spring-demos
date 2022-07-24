package dubbo.provider.anno.callback;

import com.alibaba.dubbo.config.annotation.Argument;
import com.alibaba.dubbo.config.annotation.Method;
import com.alibaba.dubbo.config.annotation.Service;
import dubbo.api.callback.CallbackListener;
import dubbo.api.callback.CallbackService;
import java.time.OffsetDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(methods = {@Method(name = "addListener", arguments = {@Argument(index = 1, callback = true)})})
public class CallbackServiceImpl implements CallbackService {

  @Override
  public String addListener(String name, CallbackListener callbackListener) {
    if (callbackListener == null) {
      log.info("Missing callbackListener...");
      return null;
    }
    return callbackListener.doListen(recordStartTime(name));
  }

  private String recordStartTime(final String name) {
    return name + " called CallbackServiceImpl at " + OffsetDateTime.now();
  }
}
