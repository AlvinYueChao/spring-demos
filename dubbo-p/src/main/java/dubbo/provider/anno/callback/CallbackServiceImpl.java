package dubbo.provider.anno.callback;

import dubbo.api.callback.CallbackListener;
import dubbo.api.callback.CallbackService;
import java.time.OffsetDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Argument;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

/**
 * 要使回调生效, callback = true 必不可少
 */
@Slf4j
@DubboService(methods = {@Method(name = "addListener", arguments = {@Argument(index = 1, callback = true)})})
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
