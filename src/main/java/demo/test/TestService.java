package demo.test;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class TestService {
  private static final Logger log = Logger.getLogger(TestService.class.getName());

  @Autowired
  private DeferredResultContainer deferredResultContainer;

  /**
   * 模擬processing..
   */
/*
  @Scheduled(fixedRate = 5000)
  public void process() {
    // log.info("processing at " + new Date());
    deferredResultContainer.updateResult();
  }
*/
  public void process(final String requestCode) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        DeferredResult<Message> dr = deferredResultContainer.get(requestCode);

        Message msg = new Message();
        msg.setCode(requestCode);
        msg.setResult("done");

        dr.setResult(msg);
      }
    }).start();
  }
}
