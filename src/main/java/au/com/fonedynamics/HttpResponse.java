package au.com.fonedynamics;

/** 
 Represents the response from a HTTP request.
*/
class HttpResponse
{
    /** 
     The status code of the response.
    */
    private int HttpStatusCode;
    final int getHttpStatusCode()
    {
            return HttpStatusCode;
    }
    final void setHttpStatusCode(int value)
    {
            HttpStatusCode = value;
    }
    /** 
     The response body.
    */
    private String Content;
    final String getContent()
    {
        return Content;
    }
    final void setContent(String value)
    {
        Content = value;
    }

    /** 
     Constructs a new HttpResponse.
    */
    HttpResponse(int httpStatusCode, String content)
    {
        // persist
        setHttpStatusCode(httpStatusCode);
        setContent(content);
    }

    /** 
     Returns the content as a string. Uses UTF8.
    */
    final String GetContentString()
    {
        return getContent();
    }

    /** 
     Gets whether the HttpStatusCode indicates success.  ie. whether it is 2xx.
    */
    final boolean getIsSuccess()
    {
        return getHttpStatusCode() >= 200 && getHttpStatusCode() < 300;
    }
}