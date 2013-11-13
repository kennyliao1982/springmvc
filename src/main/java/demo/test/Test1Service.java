package demo.test;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test1Service {
	private static final Logger log = Logger.getLogger(Test1Service.class.getName());

	@Autowired
	private TestDao testDao;

	public void saveData(Message msg) {
		testDao.create(msg);
	}
}
