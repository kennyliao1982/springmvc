package demo.test;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	private static final Logger log = Logger.getLogger(TestService.class.getName());

	@Autowired
	private DeferredResultContainer deferredResultContainer;

	/**
	 * 模擬processing..
	 */
	@Scheduled(fixedRate = 5000)
	public void process() {
		//log.info("processing at " + new Date());
		deferredResultContainer.updateResult();
	}
}
