package hack.p2p.module.smshandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



/**
 * 
 * <p>SMSHanlder -> Sending Message </p>
 * 
 * @author DhananJeyan
 *
 */
@Component
public class SMSHelper {
	
	public static final Logger log = LoggerFactory.getLogger(SMSHelper.class);

	
	@Value("${twilio.account_sid}")
	private String ACCOUNT_SID;
	
	@Value("${twilio.token}")
	private String AUTH_TOKEN;
	
	@Value("${twilio.phone_number}")
	private String fromPhoneNumber;
    
	/**
	 * <p>send - Sending the text to Client</p>
	 * 
	 * @param smsBody
	 * @param toPhoneNumber
	 */
    public void send(String smsBody, String toPhoneNumber) {
    	log.info("------Sending SMS {} to {} --------",smsBody,toPhoneNumber);
    	
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(toPhoneNumber), // to
                        new PhoneNumber(fromPhoneNumber), // from
                        smsBody).create();
        
        log.info("-----Message Sent Status {} ------", message.getStatus());
    }
}
