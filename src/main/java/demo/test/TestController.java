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
	/*
	 * @RequestMapping(value = "/function/{msg}") public String
	 * helloWorld(@PathVariable String msg, Model model) {
	 * model.addAttribute("message", msg); return "helloWorld"; }
	 */

	private static final Logger log = Logger.getLogger(TestController.class.getName());

	@Autowired
	private DeferredResultContainer deferredResultContainer;

	@RequestMapping(value = "/query/{requestCode}", method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<Message> queryProcessingResult(@PathVariable final String requestCode) {
		// 創建DeferredResult<Message>
		DeferredResult<Message> dr = new DeferredResult<Message>();
		// 當DeferredResult對客戶端響應後將其從列表中移除
		dr.onCompletion(new Runnable() {
			@Override
			public void run() {
				// TODO 自動生成的方法存根
				deferredResultContainer.remove(requestCode);
			}
		});

		deferredResultContainer.put(requestCode, dr);
		return dr;
	}

}
