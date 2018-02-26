package au.com.fonedynamics;

/** 
 The FoneDynamics client.
*/
public final class FoneDynamicsClient
{
    private static final String EX_NOT_INITIALIZED = "FoneDynamicsClient has not been initialized. Call Initialize() or configure app settings.";
    
    // The account Sid
    private String _accountSid;
    
    // The token for the account
    private String _token;
    
    // The default property Sid
    private String _defaultPropertySid;
    
    // The HttpClient
    private HttpClient _httpClient;

    /** 
     Constructs a new FoneDynamicsClient.

     @param accountSid The AccountSid that represents the account you are using.
     @param token A token associated with the account.
    */
    public FoneDynamicsClient(String accountSid, String token)
    {
            this(accountSid, token, null);
    }

    /**
      Constructs a new FoneDynamicsClient.
      @param accountSid The AccountSid that represents the account you are using.
      @param token A token associated with the account.
      @param defaultPropertySid The default PropertySid to use for requests where a PropertySid is not specified.
    */
    public FoneDynamicsClient(String accountSid, String token, String defaultPropertySid)
    {
            // persist
            _accountSid = accountSid;
            _token = token;
            _defaultPropertySid = defaultPropertySid;
            // create new http client
            _httpClient = new HttpClient(accountSid, token);
    }

    /** 
     The AccountSid to use for requests.
     * @return The AccountSid to use for requests.
    */
    public String getAccountSid()
    {
        return _accountSid;
    }

    /** 
     The token to use for requests.
     * @return token to use for requests.
    */
    public String getToken()
    {
        return _token;
    }

    /** 
     The default PropertySid to use for requests where a PropertySid is not specified.
     * @return Default PropertySid
    */
    public String getDefaultPropertySid()
    {
        return _defaultPropertySid;
    }

    /** 
     Gets the HttpClient associated with this FoneDynamicsClient.
     * @return The HttpClient
    */
    HttpClient getHttpClient()
    {
        return _httpClient;
    }
}