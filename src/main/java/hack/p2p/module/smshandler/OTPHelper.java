package hack.p2p.module.smshandler;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * <p> Generates OTP and Validate OTP </p>
 * 
 * @author DhananJeyan
 *
 */
@Component
public class OTPHelper {
	
	public static final Logger log = LoggerFactory.getLogger(OTPHelper.class);
	
	@Value("${cache.expire_min}")
	private long EXPIRE_MIN;
	
	private LoadingCache<String, Integer> otpCache;
	
	/**
	 * <p>OTPHelper - Build Cache </p>
	 */
	public OTPHelper() {
		log.info("--Inside OTPHelper---");
	
		otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
       });
	}
	
	/**
	 * <p>generateOTP - Method Generates OTP </p>
	 * 
	 * @return
	 */
	public Integer generateOTP(String phoneNumber) {
		log.info("------Inside generateOTP----------");
	
		Random random = new Random();
	    int OTP = 100000 + random.nextInt(900000);
	    otpCache.put(phoneNumber, OTP);
	    
	    log.info("-----Key Generated for {} and OTP is {} ----------",phoneNumber, OTP);

	    return OTP;
	}
	
	/**<p>validateOTP - This method validates OTP </p>
	 * 
	 * @param phoneNumber
	 * @param OTP
	 * @return
	 * @throws ExecutionException 
	 */
	public boolean validateOTP(String phoneNumber,int OTP) throws ExecutionException {
		log.info("-----Inside validateOTP----");
		
		boolean isOTPValid = Boolean.TRUE;
		
		if(otpCache.get(phoneNumber) ==  OTP) {
			isOTPValid = Boolean.FALSE;
			otpCache.invalidate(phoneNumber);
		}
		
		return isOTPValid;
	}

}
