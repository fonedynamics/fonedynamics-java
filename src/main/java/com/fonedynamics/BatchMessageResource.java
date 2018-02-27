package com.fonedynamics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/** 
*  A MessageResource also containing details of any errors occurred
* while processing the send request.
*/
public class BatchMessageResource extends MessageResource
{
    // The error message associated with the failure.
    private String Message;

    /**
     * Get The error message associated with the failure.
     * @return The error message associated with the failure.
     */
    public final String getErrorMessage()
    {
        return Message;
    }

    /** 
    * Gets whether sending the message was successful.
    * If it failed, ErrorCode and ErrorMessage will be set containing details of the error.
    * @return Whether sending the message was successful.
    */
    public final boolean getSuccessful()
    {
        return getMessageSid() != null;
    }

    /** 
     Constructs a new message resource.  This does not send the message.
     To send a message, use MessageResource.Send().

     @param to The recipient of the SMS message in E164 format (including + prefix).
     @param text The content of the SMS message.
    */
    public BatchMessageResource(String to, String text)
    {
        super(to, text, null, null, null, null, true, null, null, null, null, null, null);
    }
    
    /** 
     Constructs a new message resource.  This does not send the message.
     To send a message, use MessageResource.Send().

     @param to The recipient of the SMS message in E164 format (including + prefix).
     @param text The content of the SMS message.
     @param from
     The sender ID. This can be a mobile phone number in E164 format (including + prefix),
     an alphanumeric string, or blank or null to send from a leased number.
    */
    public BatchMessageResource(String to, String text, String from)
    {
        super(to, text, from, null, null, null, true, null, null, null, null, null, null);
    }
    
    /** 
     Constructs a new message resource.  This does not send the message.
     To send a message, use MessageResource.Send().

     @param to The recipient of the SMS message in E164 format (including + prefix).
     @param text The content of the SMS message.
     @param from
     The sender ID. This can be a mobile phone number in E164 format (including + prefix),
     an alphanumeric string, or blank or null to send from a leased number.

     @param schedule
     The UTC time in seconds since Unix Epoch to send the message.
     This can be at most 14 days in the future. If the value is in the past, the request will
     fail, unless the value is less than 1 hour in the past, in which case the request will
     succeed and the message will be sent immediately.

     @param webhookUri The callback URI to invoke when a response is received.
     @param webhookMethod
     The method to use for response callbacks. Valid options are POST and GET. Default is POST.

     @param deliveryReceipt Whether to request a delivery receipt.
     @param deliveryReceiptWebhookUri
     The callback URI to invoke when a delivery receipt is received.
     Note that DeliveryReceipt must be set to true for this to be triggered.

     @param deliveryReceiptWebhookMethod
     The method to use for delivery receipt callbacks. Valid options are POST and GET.
     Default is POST.

     @param forwardToSms
     When a response is received, forward it to this number in E164 format.

     @param forwardFromSms
     When forwarding via SMS, send from this E164 formatted number or alphanumeric sender ID.
     By default the sender ID will be the sender ID of the responding party.

     @param forwardToEmail
     When a response is received, forward it over email to this email address.

     @param forwardFromEmail
     When forwarding via email, send from this email address.  By default this is a "no reply"
     email address.

    */
    public BatchMessageResource(
        String to, 
        String text, 
        String from, 
        Long schedule, 
        String webhookUri, 
        WebhookMethod webhookMethod, 
        boolean deliveryReceipt, 
        String deliveryReceiptWebhookUri, 
        WebhookMethod deliveryReceiptWebhookMethod, 
        String forwardToSms, 
        String forwardFromSms, 
        String forwardToEmail, 
        String forwardFromEmail)
    {
        super(
            to, 
            text, 
            from, 
            schedule, 
            webhookUri, 
            webhookMethod, 
            deliveryReceipt, 
            deliveryReceiptWebhookUri, 
            deliveryReceiptWebhookMethod,
            forwardToSms,
            forwardFromSms,
            forwardToEmail,
            forwardFromEmail);
    }
    
    /** 
     Sends the specified message.

     @param messages The message to send constructed using the MessageResource constructor.
     @param fdxClient
     The FoneDynamicsClient instance to use.

     @return The MessageResource that was sent, or an exception on failure.
     @throws java.io.IOException
    */
    public static List<BatchMessageResource> send(
            Iterable<MessageResource> messages,
            FoneDynamicsClient fdxClient) throws IOException
    {
        return send(messages, fdxClient.getDefaultPropertySid(), fdxClient);
    }
    
    /** 
     Sends multiple messages at once.  Individual messages can succeed or fail using this request.
     The details of successful and failed messages is available in the response list.

     @param messages The message to send constructed using the MessageResource constructor.
     @param propertySid
     The PropertySid of the property against which to send the message.

     @param fdxClient
     The FoneDynamicsClient instance to use.

     @return The MessageResource that was sent, or an exception on failure.
     @throws java.io.IOException
    */
    public static List<BatchMessageResource> send(
            Iterable<MessageResource> messages,
            String propertySid,
            FoneDynamicsClient fdxClient) throws IOException
    {
        // validate
        if (messages == null) throw new IllegalArgumentException("messages must be provided");
         
        for (MessageResource param: messages) {
            if (param.getMessageSid() != null &&
                !param.getMessageSid().isEmpty())
            {
                throw new IllegalStateException("Cannot send an existing message.");                
            }
        }
    
        // construct the request
        Request request = new Request(
            HttpMethod.Post,
            "/v2/Properties/" + propertySid + "/BatchMessages",
            fdxClient.getAccountSid(), 
            fdxClient.getToken());

        Gson gson = new GsonBuilder().create();
        BatchMessageRequest batchRequest = new BatchMessageRequest(messages);
        
        String requestJson = gson.toJson(batchRequest);
        // set the request body
        request.SetBody(requestJson, "application/json");

        // perform the request and get the response
        HttpResponse response = fdxClient.getHttpClient().Send(request);

        // throw if failed
        if (!response.getIsSuccess()) throw ErrorResponse.CreateException(response);
        String responseJson = response.GetContentString();
        
        // deserialise and return
        Type listType = new TypeToken<ArrayList<BatchMessageResource>>(){}.getType();
        List<BatchMessageResource> msgResponse = gson.fromJson(responseJson, listType);
        
        return msgResponse;
    }
}