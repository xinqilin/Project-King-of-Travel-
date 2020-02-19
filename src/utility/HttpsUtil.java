package utility;

import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/* 1. �z�Ljavax.net.ssl.X509TrustManager�����H¶�Lhttps�{��
 * 2. �o�O�ѨM�]��PKIX���|�Ыإ��ѮɡA���ͥH�U�T��Exception�ɪ��ѨM�覡  
 *      - javax.net.ssl.SSLHandshakeException
 *   	  - sun.security.validator.ValidatorException
 *   	  - sun.security.provider.certpath.SunCertPathBuilderException
 * */

public class HttpsUtil {
	
	public static URLConnection getURLConnection (String httpsURL) throws Exception {
		
		// �z�Ljavax.net.ssl.X509TrustManager�����H¶�Lhttps�{��
		TrustManager[] trustManagers = new TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
			@Override
			public X509Certificate[] getAcceptedIssuers() {return null;}
		} };
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, trustManagers, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

		// �Ыإ��H�����D���W�����Ҿ� Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {return true;}
		};
		// �w�˥��H�����D�����Ҿ� Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		URL url = new URL(httpsURL);
		URLConnection urlConn = url.openConnection();
		return urlConn;
		
	}
}
