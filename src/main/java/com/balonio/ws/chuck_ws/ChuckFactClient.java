package com.balonio.ws.chuck_ws;

import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientProperties;

@Dependent
public class ChuckFactClient {
  ChuckFact response;
  Client client;
  String url ;
  private final static Logger LOGGER = Logger.getLogger(ChuckFactClient.class.getName());
  TrustManager[] certs;

  @PostConstruct
    protected void init(){
      url = "https://chuck-api.balonio.com/jokes/random";
      certs = new TrustManager[]{new X509TrustManager() {
        @Override
          public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
          throws java.security.cert.CertificateException {
          // TODO Auto-generated method stub

          }
        @Override
          public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
          throws java.security.cert.CertificateException {
          // TODO Auto-generated method stub

          }
        @Override
          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
          }
      }
      };
      SSLContext ctx = null;
      try {
        ctx = SSLContext.getInstance("TLS");
        ctx.init(null, certs, new SecureRandom());
      } catch (java.security.GeneralSecurityException e) {
        LOGGER.severe(e.toString());

      }

      client = ClientBuilder.newBuilder()
        .sslContext(ctx)
        .hostnameVerifier(new HostnameVerifier() {
          @Override
          public boolean verify(String hostname, SSLSession session) {
            return true;
          }
        })
      .build();
      client.property(ClientProperties.CONNECT_TIMEOUT, 5000);


    }
  private ChuckFact get_request_with_retry(WebTarget target, int retryCount, int retryHold){
    ChuckFact chuckFact = null;

    LOGGER.info("Fetching request");
    try{
      chuckFact = target.request(MediaType.APPLICATION_JSON).get(ChuckFact.class);
    }catch(ProcessingException e){

      LOGGER.log(Level.SEVERE,e.toString());
      LOGGER.log(Level.INFO,"Retrying");
      try{
        Thread.sleep((retryHold));
        chuckFact = target.request(MediaType.APPLICATION_JSON).get(ChuckFact.class);
      }catch(ProcessingException | InterruptedException r){
        LOGGER.log(Level.SEVERE,r.toString());
        LOGGER.log(Level.INFO,"Unable to get FACTafter retrying transaction");

      }

    }
    LOGGER.info("done");

    return chuckFact;


  }
  public String getRandom(){
    WebTarget target;
    target = client.target(url);
    int hold_time = Integer.parseInt(System.getProperty("com.balonio.ws.chuck_ws.retry_hold", "5000"));
    int retry_count = Integer.parseInt(System.getProperty("com.balonio.ws.chuck_ws.retry_count", "2"));
    ChuckFact chuckFact = get_request_with_retry(target,retry_count,hold_time);
    return chuckFact.getValue();
  }

}
