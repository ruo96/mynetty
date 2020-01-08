package com.wrh.big.utils;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 
 * @author zhangjing3
 *
 */
public class TrustAnyX509TrustManager implements X509TrustManager{
	/**  空方法信任所有客户端证书
	 * @param chain
	 * @param authType
	 * @throws CertificateException
	 */
	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
	{
		// TODO Auto-generated method stub

	}

	/**空方法信任所有服务器证书
	 * @param chain
	 * @param authType
	 * @throws CertificateException
	 */
	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
	{
		// TODO Auto-generated method stub

	}

	/**返回信任的证书
	 * @return
	 */
	@Override
	public X509Certificate[] getAcceptedIssuers()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
