package com.bluestone.todolistapp.utility;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.bluestone.todolistapp.model.LoginUserData;
import com.bluestone.todolistapp.model.UserData;

public class EncryptionUtil {
	private static String KEY = "1991!@#$06!@#$04"; // 128 bit key
    private static String INIT_VECTOR = "RandomInitVector"; // 16 bytes IV
    private static Long TIME_OUT = 10000000L;
    
	public static String encrypt(String value) {
		if(value == null || "".equals(value)) return "";
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String value) {
    	if(value == null || "".equals(value)) return "";
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(value));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public static String generateEncryptedToken(UserData user) {
		String dateTime = ServerUtil.getCurrentDateTime();
		String token = user.getId() + "||" + user.getCode() + "||" + user.getName()+"||" + dateTime;
		return encrypt(token);
	}
    
    public static String generateEncryptedTokenForLogin(LoginUserData user) {
		String dateTime = ServerUtil.getCurrentDateTime();
		String token = user.getId() + "||" + user.getCode() + "||" + user.getName()+"||" + dateTime;
		return encrypt(token);
	}
    
    public static UserData decodeToken(String token) throws Exception{
    	UserData result = new UserData();
    	String decryptedToken = decrypt(token);
    	String[] values = decryptedToken.split("\\|\\|");
    	long currentDateTime = Long.parseLong(ServerUtil.getCurrentDateTime());
    	
    	if(values.length != 4) throw new Exception("Invalid Token");
    	int i = 0;
    	result.setId(values[i++]);
    	result.setCode(values[i++]);
    	result.setName(values[i++]);
    	
    	if(currentDateTime - Long.parseLong(values[i++]) > TIME_OUT)
    		return null;
    	
    	return result;
    }
    
    public static void main(String[] args) throws Exception {
    	//System.out.println(new ObjectMapper().writeValueAsString(new LoginUserData()));
    }
}
