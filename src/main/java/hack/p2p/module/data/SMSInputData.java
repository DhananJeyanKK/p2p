package hack.p2p.module.data;

/**
 * <p>SMSInputData - Input data helper for SMS </p>
 * 
 * @author DhananJeyan
 *
 */
public class SMSInputData {
	
	private String phoneNumber;

	private int OTP;
	
	private String text;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getOTP() {
		return OTP;
	}

	public void setOTP(int oTP) {
		OTP = oTP;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
