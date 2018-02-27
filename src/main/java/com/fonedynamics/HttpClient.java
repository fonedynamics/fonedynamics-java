package com.fonedynamics;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/** 
 Client to make HTTP requests.
*/
class HttpClient {
    
    // The api host
    private static final String API_HOST = "api.fonedynamics.com";
    
    // The credentials provider
    private final CredentialsProvider CredsProvider;
        
    /**
     * Constructor
     * @param accountSid The account Sid
     * @param token The token for the account
    */
    HttpClient(String accountSid, String token)
    {
        CredsProvider = new BasicCredentialsProvider();
        CredsProvider.setCredentials(
                new AuthScope(API_HOST, 443),
                new UsernamePasswordCredentials(accountSid, token));
    }
    
    // Sends the specified request and returns the response.
    HttpResponse Send(Request request) throws UnsupportedEncodingException, IOException
    {
        CloseableHttpClient httpClient = GetHttpClient();

        HttpRequestBase httpRequest = CreateHttpRequest(request);

        try (CloseableHttpResponse response = httpClient.execute(httpRequest)) {
            String json = EntityUtils.toString(response.getEntity());
            return new HttpResponse(response.getStatusLine().getStatusCode(), json);
        } 
        finally
        {
            try
            {
                httpClient.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(Request.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }
    
    // Creates the HTTP request.
    HttpRequestBase CreateHttpRequest(Request request) throws UnsupportedEncodingException
    {
        // create request message
        HttpRequestBase requestBase = null;
        switch (request.getMethod())
        {
            case Get:
                requestBase = new HttpGet(request.CreateUri());
                break;

            case Post:
                HttpPost httpPost = new HttpPost(request.CreateUri());
                // configure request body
                StringEntity stringEntity = new StringEntity(request.getRequestBody());
                httpPost.setEntity(stringEntity );
                requestBase = httpPost;
                break;
        }

        if (requestBase != null)
        {
            requestBase.addHeader("Accept",  "application/json");
            requestBase.addHeader("AcceptEncoding",  "utf-8");
            requestBase.addHeader("Content-type", request.getContentType());
        }

        return requestBase;
    }
    
    // Returns HttpClient instance
    private CloseableHttpClient GetHttpClient()
    {
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(CredsProvider)
                .build();
        
        return httpclient;
    }
}
