package org.acme.quickstart;

import java.net.Socket;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.enterprise.context.ApplicationScoped;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

@ApplicationScoped // <1>
public class TrustAllFruityViceService {

  public FruityVice getFruitByName(String name) { 
    FruityViceService fruityViceService = RestClientBuilder.newBuilder()
      .baseUri(URI.create("https://www.fruityvice.com/"))
      .hostnameVerifier(NoopHostnameVerifier.INSTANCE) // <2>
      .sslContext(trustEverything()) // <3>
      .build(FruityViceService.class);

    return fruityViceService.getFruitByName(name);
  }

  private static SSLContext trustEverything() { // <4>

    try {
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts(), new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
      return sc;
    } catch (KeyManagementException | NoSuchAlgorithmException e) {
      throw new IllegalStateException(e);
    }
  }

  private static TrustManager[] trustAllCerts() {
    return  new TrustManager[]{
      new X509ExtendedTrustManager(){

        @Override
        public X509Certificate[] getAcceptedIssuers() {
          return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, 
                                       String authType) 
          throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, 
                                       String authType) 
          throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, 
                                      String authType, 
                                      SSLEngine sslEngine) 
          throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, 
                                       String authType, 
                                       Socket socket) 
          throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, 
                                       String authType, 
                                       SSLEngine sslEngine) 
          throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, 
                                       String authType, 
                                       Socket socket) 
          throws CertificateException {
        }
      }
    };  
  } 
}
