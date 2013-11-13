package demo.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

@Component
public class DeferredResultContainer {

  private static final Logger log = Logger.getLogger(DeferredResultContainer.class.getName());

  private final Map<String, DeferredResult<Message>> processingRequests = new ConcurrentHashMap<String, DeferredResult<Message>>();

  public void put(String requestCode, DeferredResult<Message> dr) {
    if (!processingRequests.containsKey(requestCode)) {
      processingRequests.put(requestCode, dr);
    }
  }

  public void remove(String requestCode) {
    processingRequests.remove(requestCode);
  }

  public DeferredResult<Message> get(String requestCode) {
    return processingRequests.get(requestCode);
  }

}
