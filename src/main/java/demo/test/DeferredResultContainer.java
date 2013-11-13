package demo.test;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

@Component
public class DeferredResultContainer {

	private static final Logger log = Logger.getLogger(DeferredResultContainer.class.getName());
	
	private final Map<String, DeferredResult<Message>> processingRequests = new ConcurrentHashMap<String, DeferredResult<Message>>();
	
	
	public void put(String requestCode, DeferredResult<Message> dr){
		if(!processingRequests.containsKey(requestCode)){
			processingRequests.put(requestCode, dr);
		}
	}
	
	public void remove(String requestCode){
		processingRequests.remove(requestCode);
	}
	
	public void updateResult(){
		for(Entry<String,DeferredResult<Message>> entry : processingRequests.entrySet()){
			String requestCode = entry.getKey();
			DeferredResult<Message> dr = entry.getValue();
			
			Message msg = new Message();
			msg.setCode(requestCode);
			msg.setResult("done");
			
			dr.setResult(msg);
		}
	}
	
}
