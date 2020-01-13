package com.wrh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 类Md5Utils.java的实现描述：MD5工具类
 * 
 */
public class Md5Utils {
	private static char Digit[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    /** 16-常量 **/
    private final static int I_SIXTEEN = 16;
    /** 32-常量 **/
    private final static int I_THIRTY_TWO = 32;
    /** 1024-边界 **/
    private final static int DEFALUT_BOUND_SIZE = 1024;

	public static String byteHEX(byte ib) {
		char ob[] = new char[2];
		ob[0] = Digit[ib >>> 4 & 0xf];
		ob[1] = Digit[ib & 0xf];
		String s = new String(ob);
		return s;
	}

	public static String byteHEX(byte[] data) {
		char[] temp = new char[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			byte b = data[i];
			temp[i * 2] = Digit[b >>> 4 & 0x0f];
			temp[i * 2 + 1] = Digit[b & 0x0f];
		}
		return new String(temp);

	}

	public static String encrypt(String source) {
		String s;
		try {
			MessageDigest std = MessageDigest.getInstance("MD5", "SUN");
			byte digest[] = std.digest(source.getBytes());
			String digestHexStr = "";
            for (int i = 0; i < I_SIXTEEN; i++) {
				digestHexStr = String.valueOf(digestHexStr)
						+ String.valueOf(byteHEX(digest[i]));

			}
			String s1 = digestHexStr;
			return s1.toLowerCase();
		} catch (Exception e) {
			s = "";
		}
		return s;
	}

	public static String encrypt(byte[] source) {
		String s;
		try {
			MessageDigest std = MessageDigest.getInstance("MD5", "SUN");
			byte digest[] = std.digest(source);
			String digestHexStr = "";
            for (int i = 0; i < I_SIXTEEN; i++) {
				digestHexStr = String.valueOf(digestHexStr)
						+ String.valueOf(byteHEX(digest[i]));

			}
			String s1 = digestHexStr;
			return s1.toLowerCase();
		} catch (Exception e) {
			s = "";
		}
		return s.toLowerCase();
	}

	public static String getFileMD5(File file) throws IOException,
			NoSuchAlgorithmException {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[8192];
		int len;
		digest = MessageDigest.getInstance("MD5");
		in = new FileInputStream(file);
        while ((len = in.read(buffer, 0, DEFALUT_BOUND_SIZE)) != -1) {
			digest.update(buffer, 0, len);
		}
		in.close();
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}

	public static String getFileMD5(InputStream inputStream)
			throws IOException, NoSuchAlgorithmException {
		MessageDigest digest = null;
		byte buffer[] = new byte[8192];
		int len;
		digest = MessageDigest.getInstance("MD5");
        while ((len = inputStream.read(buffer, 0, DEFALUT_BOUND_SIZE)) != -1) {
			digest.update(buffer, 0, len);
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}

	public static boolean isMd5(String md5) {
        if (md5.length() != I_THIRTY_TWO) {
			return false;
		}
		String upper = md5.toUpperCase();
		for (int i = 0; i < upper.length(); i++) {
			boolean res = false;
			for (char c : Digit) {
				if (c == upper.charAt(i)) {
					res = true;
				}
			}
			if (res == false) {
				return false;
			}
		}
		return true;
	}
}
