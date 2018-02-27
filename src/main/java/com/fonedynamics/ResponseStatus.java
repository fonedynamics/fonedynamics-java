package com.fonedynamics;

/** 
 The ResponseStatus object.
*/
public class ResponseStatus
{
    // The error code associated with the response status.
    private String ErrorCode;

    /** 
     Get the error code associated with the response status.
     * @return The error code associated with the response status.
    */
    public final String getErrorCode()
    {
        return ErrorCode;
    }
    final void setErrorCode(String value)
    {
        ErrorCode = value;
    }

    // The message associated with the error code.
    private String Message;

    /** 
     Get the message associated with the error code.
     * @return The message associated with the error code.
    */
    public final String getMessage()
    {
        return Message;
    }
    final void setMessage(String value)
    {
        Message = value;
    }
}