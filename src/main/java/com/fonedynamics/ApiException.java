package com.fonedynamics;

/** 
 Represents an exception that occurred during an API request.
*/
public class ApiException extends RuntimeException
{
    // The status code of the HTTP request.
    private int HttpStatusCode;

    /**
     * Get the status code of the HTTP request.
     * @return status code of the HTTP request.
     */
    public final int getHttpStatusCode()
    {
        return HttpStatusCode;
    }
    
    // Set the status code of the HTTP request.
    private void setHttpStatusCode(int value)
    {
        HttpStatusCode = value;
    }
    
     // The error code as per https://www.fonedynamics.com/docs/rest-api/errors/
    private String ErrorCode;

    /**
     * Get the error code as per https://www.fonedynamics.com/docs/rest-api/errors/
     * @return error code
     */
    public final String getErrorCode()
    {
        return ErrorCode;
    }
    
    // sets error code
    private void setErrorCode(String value)
    {
        ErrorCode = value;
    }
    
    /** 
     Constructs a new ApiException.
     * @param httpStatusCode The HTTP status code
     * @param errorCode The error code
     * @param message The error message
    */
    public ApiException(int httpStatusCode, String errorCode, String message)
    {
        super(message);
        // persist
        setHttpStatusCode(httpStatusCode);
        setErrorCode(errorCode);
    }
}