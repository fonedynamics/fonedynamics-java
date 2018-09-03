package com.fonedynamics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 Represents an error resource.
*/
class ErrorResponse
{
    // Information about the response.
    private ResponseStatus ResponseStatus;

    /**
     * Get Information about the response.
     * @return Information about the response.
    */
    final ResponseStatus getResponseStatus()
    {
        return ResponseStatus;
    }

    /**
     * Sets response status
     * @param value The response status
    */
    final void setResponseStatus(ResponseStatus value)
    {
        ResponseStatus = value;
    }

    /** 
     Creates an exception from the provided response.
     * @param response The Http response
     * @return Runtime exception with response details
    */
    static RuntimeException CreateException(HttpResponse response)
    {
        // deserialise response
        Gson gson = new GsonBuilder().create();
        try
        {
            ErrorResponse error = gson.fromJson(response.GetContentString(), ErrorResponse.class);
            // return exception
            return new ApiException(response.getHttpStatusCode(), error.getResponseStatus().getErrorCode(), error.getResponseStatus().getMessage());
        }
        catch (JsonParseException ex)
        {
            // response did not contain JSON or it was malformed
            Logger.getLogger(Request.class.getName()).log(Level.WARNING, null, ex);
            return new ApiException(response.getHttpStatusCode(), "UNKNOWN_ERROR", "An error occurred. Please retry the request.");
        }
    }
}
