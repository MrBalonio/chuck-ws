package com.balonio.ws.chuck_ws;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.SslConfigurator;

@Path("/fact")
public class ChuckResource {

	@Path("/random")
	@GET
	public String getFact(){
		String response;
    	Client client;
    	String url ;
    	url = "https://chuck-api.balonio.com/jokes/random";

    	TrustManager[] certs = new TrustManager[]{new X509TrustManager() {


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
        }};      
    	 SSLContext ctx = null;
         try {
             ctx = SSLContext.getInstance("TLS");
             ctx.init(null, certs, new SecureRandom());
         } catch (java.security.GeneralSecurityException e) {
  
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
		WebTarget target;
		target = client.target(url);
		response = target.request().get(String.class);
		
		
		return response;
	}
}
