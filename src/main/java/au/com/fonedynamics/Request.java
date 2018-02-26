package au.com.fonedynamics;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.utils.URIBuilder;

/** 
 Describes a request to an external endpoint.
*/
class Request
{
    /** 
     Platform version and details string to use in the User-Agent.
    */
    private static final String PLATFORM = ".NET (.NET Framework 4.5+)";
    /** 
     The hostname of the API.
    */
    private static final String API_HOST = "https://api.fonedynamics.com";
    //private static final String API_HOST = "https://requestb.in/149e8af1";

    /** 
     The path to make the request to.
    */
    private String Path;
    final String getPath()
    {
        return Path;
}
    final void setPath(String value)
    {
        Path = value;
    }

    /** 
     Optional list of query string parameters.
    */
    private ArrayList<AbstractMap.SimpleEntry<String, String>> QueryParameters;
    final ArrayList<AbstractMap.SimpleEntry<String, String>> getQueryParameters()
    {
        return QueryParameters;
    }
    final void setQueryParameters(ArrayList<AbstractMap.SimpleEntry<String, String>> value)
    {
        QueryParameters = value;
    }

    /** 
     The HTTP method to use for the request.
    */
    private HttpMethod Method = HttpMethod.values()[0];
    final HttpMethod getMethod()
    {
        return Method;
    }
    final void setMethod(HttpMethod value)
    {
        Method = value;
    }

    /** 
     Optional body of the request.
    */
    private String RequestBody;

    final String getRequestBody()
    {
        return RequestBody;
    }

    final void setRequestBody(String value)
    {
        RequestBody = value;
    }

    /** 
     Optional content type of the request.
    */
    private String ContentType;
    final String getContentType()
    {
        return ContentType;
    }
    final void setContentType(String value)
    {
        ContentType = value;
    }

    /** 
     The AccountSid to use for this request.
    */
    private String AccountSid;
    final String getAccountSid()
    {
        return AccountSid;
    }
    final void setAccountSid(String value)
    {
        AccountSid = value;
    }

    /** 
     The token to use for this request.
    */
    private String Token;
    final String getToken()
    {
        return Token;
    }
    final void setToken(String value)
    {
        Token = value;
    }

    /** 
     Constructs a new request.
    */
    Request(HttpMethod method, String path, String accountSid, String token)
    {
        // persist
        setMethod(method);
        setPath(path);
        setAccountSid(accountSid);
        setToken(token);
    }

    /** 
     Adds a new query parameter to the request.
    */
    final void AddQueryParameter(String key, String value)
    {
        if (getQueryParameters() == null)
        {
            setQueryParameters(new ArrayList<AbstractMap.SimpleEntry<String, String>>());
        }
        getQueryParameters().add(new AbstractMap.SimpleEntry<>(key, value));
    }

    /** 
     Sets the body of the request.
    */
    final void SetBody(String requestBody, String contentType)
    {
        setRequestBody(requestBody);
        setContentType(contentType);
    }

    /** 
     Returns the URI for this request.
    */
    final URI CreateUri()
    {
        try {
            URIBuilder builder = new URIBuilder(API_HOST + getPath());
            //URIBuilder builder = new URIBuilder(API_HOST);
                        
            ArrayList<AbstractMap.SimpleEntry<String, String>> queryParameters = getQueryParameters();
            
            if (queryParameters != null)
            {
                for (Map.Entry<String, String> param: queryParameters) {
                    builder.setParameter(param.getKey(), param.getValue());
                }
            }
            
            return builder.build();
        } 
        catch (URISyntaxException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}