package demo.test;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class TestController {

  private static final Logger log = Logger.getLogger(TestController.class.getName());

  @Autowired
  private DeferredResultContainer deferredResultContainer;

  @Autowired
  private TestService testService;

  @RequestMapping(value = "/query/{requestCode}", method = RequestMethod.GET)
  @ResponseBody
  public DeferredResult<Message> queryProcessingResult(@PathVariable final String requestCode) {
    DeferredResult<Message> dr = new DeferredResult<Message>();
    dr.onCompletion(new Runnable() {
      @Override
      public void run() {
        deferredResultContainer.remove(requestCode);
      }
    });

    deferredResultContainer.put(requestCode, dr);
    testService.process(requestCode);
    return dr;
  }

}
