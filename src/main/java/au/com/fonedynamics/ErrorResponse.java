package au.com.fonedynamics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        ErrorResponse error = gson.fromJson(response.GetContentString(), ErrorResponse.class);
        // return exception
        return new ApiException(response.getHttpStatusCode(), error.getResponseStatus().getErrorCode(), error.getResponseStatus().getMessage());
    }
}
