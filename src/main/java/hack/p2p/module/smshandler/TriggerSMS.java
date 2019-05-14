package hack.p2p.module.smshandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hack.p2p.module.data.SMSInputData;

/**
 * <p> TriggerSMS - Helps to Trigger SMS </p>
 * 
 * @author DhananJeyan
 *
 */
@Component
public class TriggerSMS {
	
	public static final Logger log = LoggerFactory.getLogger(TriggerSMS.class);
	
	@Autowired
	OTPHelper otpHelper;
	
	@Autowired
	SMSHelper smsHelper;
	
	/**<p>triggerSMS - Helps to Trigger SMS </p>
	 * 
	 * @param smsInputData
	 */
	public void triggerSMS(SMSInputData smsInputData) {
		log.info("---Inside triggerSMS--");
		
		Integer otp = otpHelper.generateOTP(smsInputData.getPhoneNumber());
		smsHelper.send(getsmsBody(smsInputData.getText(),otp), smsInputData.getPhoneNumber());
	}

	/**
	 * <p>getsmsBody - get SMS Body message </p>
	 * 
	 * @param text
	 * @param otp
	 * @return
	 */
	private String getsmsBody(String text, Integer otp) {
		return text+otp;
	}
}
