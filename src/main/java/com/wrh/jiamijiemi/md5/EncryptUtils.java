package com.wrh.jiamijiemi.md5;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 加密处理
 * @author lance
 * @since 2018.1.7 17:31
 */
public final class EncryptUtils {
	
	private EncryptUtils(){
		
	}
	
	/**
	 * Base64加密
	 * @author lance
	 */
	public synchronized static String encodeBase64(String password){
		if(StringUtils.isBlank(password)) {
			return "";
		}

        return Base64.getEncoder().encodeToString(password.getBytes());
    }
	
	/**
	 * Base64解密
	 * @author lance
	 */
	public synchronized static String decodeBase64(String password){
		if(StringUtils.isBlank(password)) {
			return "";
		}
		byte[] bytes = Base64.getDecoder().decode(password);
        return new String(bytes);
    }
	
	/**
	 * 采用MD5加密
	 * @param password
	 * @author lance
	 */
	public synchronized static String MD5(String password){
		return MD5(password, null);
	}
	
	/**
	 * 采用MD5和手机号码部分加密
	 * @param password
	 * @author lance
	 */
	public synchronized static String MD5(String password, String salt){
		Charset utf8 = Charset.forName("UTF-8");
		if(StringUtils.isNotBlank(salt)) {
		    password = salt+password+salt;
		}
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes(utf8));

			byte[] b = md5.digest();
			StringBuilder builder = new StringBuilder();
	    	for (int i=0; i<b.length; i++) {
	    		String hex = Integer.toHexString(0xff & b[i]);
	   	     	if(hex.length()==1) {builder.append('0');}
	   	     	builder.append(hex);
	    	}
			return builder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}