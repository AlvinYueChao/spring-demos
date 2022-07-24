package dubbo.api.callback;

public interface CallbackService {
  String addListener(final String name, final CallbackListener callbackListener);
}
