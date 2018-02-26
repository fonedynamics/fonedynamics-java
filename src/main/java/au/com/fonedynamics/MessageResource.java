package au.com.fonedynamics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

/** 
 Represents a message and contains messaging functionality.
*/
public class MessageResource {
    
    // The account secure identifier associated with the message.
    private String MessageSid;

    /** 
     Get the account secure identifier associated with the message.
     * @return The account secure identifier associated with the message.
    */
    public final String getMessageSid()
    {
        return MessageSid;
    }
    final void setMessageSid(String value)
    {
        MessageSid = value;
    }

    // The alphanumeric or E164 formatted sender ID of the message.
    private String From;

    /** 
     Get the alphanumeric or E164 formatted sender ID of the message.
     If composing a message, a null or empty value indicates that a number should
     be dynamically leased for this message.
     * @return The alphanumeric or E164 formatted sender ID of the message.
    */
    public final String getFrom()
    {
        return From;
    }
    final void setFrom(String value)
    {
        From = value;
    }

    // The E164 formatted recipient of the SMS message.
    private String To;

    /** 
     Get the E164 formatted recipient of the SMS message.
     * @return The E164 formatted recipient of the SMS message.
    */
    public final String getTo()
    {
        return To;
    }
    final void setTo(String value)
    {
        To = value;
    }

    // The content of the SMS message.
    private String Text;

    /** 
     Get the content of the SMS message.
     * @return The content of the SMS message.
    */
    public final String getText()
    {
        return Text;
    }
    final void setText(String value)
    {
        Text = value;
    }

    // True if a delivery receipt was requested and false otherwise.
    private boolean DeliveryReceipt;
    
    /** 
     Set to true if a delivery receipt was requested and false otherwise.
     * @return True if a delivery receipt was requested and false otherwise.
    */
    public final boolean getDeliveryReceipt()
    {
        return DeliveryReceipt;
    }
    final void setDeliveryReceipt(boolean value)
    {
        DeliveryReceipt = value;
    }

    // The number of message segments.
    private Integer NumSegments = null;
    
    /** 
     Get The number of message segments.
     * @return the number of message segments.
    */
    public final Integer getNumSegments()
    {
        return NumSegments;
    }
    final void setNumSegments(Integer value)
    {
        NumSegments = value;
    }
    
    // The message status.
    private String Status = null;
    
    /** 
     Get the message status. See below table for possible values.
     * @return The message status
    */
    public final String getStatus()
    {
        return Status;
    }
    final void setStatus(String value)
    {
        Status = value;
    }

    // The direction of the message.
    MessageDirection Direction;

    /** 
     Get the direction of the message.
     * @return The direction of the message.
    */
    public final MessageDirection getDirection()
    {
        return Direction;
    }
    final void setDirection(MessageDirection value)
    {
        Direction = value;
    }

    // Timestamp of when the message is/was scheduled to be sent.
    private Long Scheduled = null;
    
    /** 
     Get timestamp of when the message is/was scheduled to be sent.
     * @return Timestamp of when the message is/was scheduled to be sent.
    */
    public final Long getScheduled()
    {
        return Scheduled;
    }
    final void setScheduled(Long value)
    {
        Scheduled = value;
    }

    // Timestamp of when the message resource was created.
    private Long Created = null;
    /** 
     Get timestamp of when the message resource was created.
     * @return Timestamp of when the message resource was created.
    */
    public final Long getCreated()
    {
        return Created;
    }
    final void setCreated(Long value)
    {
        Created = value;
    }

    // Ttimestamp of when the message was submitted to the SMS Center.
    private Long Submitted = null;

    /** 
     Get timestamp of when the message was submitted to the SMS Center.
     * @return Timestamp of when the message was submitted to the SMS Center.
    */
    public final Long getSubmitted()
    {
        return Submitted;
    }
    final void setSubmitted(Long value)
    {
        Submitted = value;
    }

    // Timestamp of when the message delivery occurred.
    private Long Delivered = null;
    /** 
     Get timestamp of when the message delivery occurred.
     * @return Timestamp of when the message delivery occurred.
    */
    public final Long getDelivered()
    {
        return Delivered;
    }
    final void setDelivered(Long value)
    {
        Delivered = value;
    }

    // Timestamp of when an inbound message was received.
    private Long Received = null;

    /** 
     Get timestamp of when an inbound message was received.
     * @return Timestamp of when an inbound message was received.
    */
    public final Long getReceived()
    {
        return Received;
    }
    final void setReceived(Long value)
    {
        Received = value;
    }

    // Error code (if message submission or delivery failed).
    private String ErrorCode;

    /** 
     Get error code (if message submission or delivery failed).
     * @return Error code
    */
    public final String getErrorCode()
    {
        return ErrorCode;
    }
    final void setErrorCode(String value)
    {
        ErrorCode = value;
    }

    // The UTC time in seconds since Unix Epoch to send the message.
    private Long Schedule = null;

    /** 
     Get the UTC time in seconds since Unix Epoch to send the message. This can be at most
     14 days in the future. If the value is in the past, the request will fail, unless
     the value is less than 1 hour in the past, in which case the request will succeed
     and the message will be sent immediately.
     * @return The UTC time in seconds since Unix Epoch to send the message.
    */
    public final Long getSchedule()
    {
        return Schedule;
    }
    final void setSchedule(Long value)
    {
        Schedule = value;
    }

    // The callback URI to invoke when a delivery receipt is received. Note that
    private String DeliveryReceiptWebhookUri;
    /** 
     Get the callback URI to invoke when a delivery receipt is received. Note that
     DeliveryReceipt must be set to true for this to be triggered.
     * @return The callback URI to invoke when a delivery receipt is received
    */
    public final String getDeliveryReceiptWebhookUri()
    {
        return DeliveryReceiptWebhookUri;
    }
    final void setDeliveryReceiptWebhookUri(String value)
    {
        DeliveryReceiptWebhookUri = value;
    }

    // The method to use for delivery receipt callbacks. Valid options are POST and GET.
    private WebhookMethod DeliveryReceiptWebhookMethod;
    /** 
     Get the method to use for delivery receipt callbacks. Valid options are POST and GET.
     Default is POST.
     * @return the method to use for delivery receipt callbacks
    */
    public final WebhookMethod getDeliveryReceiptWebhookMethod()
    {
        return DeliveryReceiptWebhookMethod;
    }
    final void setDeliveryReceiptWebhookMethod(WebhookMethod value)
    {
        DeliveryReceiptWebhookMethod = value;
    }

    // When a response is received, forward it to this number in E164 format.
    private String ForwardToSms;

    /** 
     When a response is received, forward it to this number in E164 format.
     * @return Forwarding number
    */
    public final String getForwardToSms()
    {
        return ForwardToSms;
    }
    final void setForwardToSms(String value)
    {
        ForwardToSms = value;
    }

    // When forwarding via SMS, send from this E164 formatted number or alphanumeric sender ID
    private String ForwardFromSms;
    
    /** 
     When forwarding via SMS, send from this E164 formatted number or alphanumeric sender
     ID. By default the sender ID will be the sender ID of the responding party.
     * @return Sent from number
    */
    public final String getForwardFromSms()
    {
        return ForwardFromSms;
    }
    final void setForwardFromSms(String value)
    {
        ForwardFromSms = value;
    }

    // When a response is received, forward it over email to this email address.
    private String ForwardToEmail;

    /** 
     When a response is received, forward it over email to this email address.
     * @return Forwarding email
    */
    public final String getForwardToEmail()
    {
        return ForwardToEmail;
    }
    final void setForwardToEmail(String value)
    {
        ForwardToEmail = value;
    }

    // When forwarding via email, send from this email address
    private String ForwardFromEmail;
    
    /** 
     When forwarding via email, send from this email address. By default this is a
     "no reply" email address.
     * @return sent from email address
    */
    public final String getForwardFromEmail()
    {
        return ForwardFromEmail;
    }
    final void setForwardFromEmail(String value)
    {
        ForwardFromEmail = value;
    }

    // The callback URI to invoke when a response is received.
    private String WebhookUri;

    /** 
     The callback URI to invoke when a response is received.
     * @return callback URI
    */
    public final String getWebhookUri()
    {
        return WebhookUri;
    }
    final void setWebhookUri(String value)
    {
        WebhookUri = value;
    }

    // The method to use for response callbacks
    private WebhookMethod WebhookMethod;
    
    /** 
     The method to use for response callbacks. Valid options are POST and GET.
     Default is POST.
     * @return method to use for response callbacks
    */
    public final WebhookMethod getWebhookMethod()
    {
        return WebhookMethod;
    }
    final void setWebhookMethod(WebhookMethod value)
    {
        WebhookMethod = value;
    }

    /** 
        Constructs a new message resource.  This does not send the message.
        To send a message, use MessageResource.Send().

        @param to The recipient of the SMS message in E164 format (including + prefix).
        @param text The content of the SMS message.
    */
    public MessageResource(String to, String text)
    {
        this(to, text, null, null, null, null, true, null, null, null, null, null, null);
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
    public MessageResource(String to, String text, String from)
    {
        this(to, text, from, null, null, null, true, null, null, null, null, null, null);
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
    public MessageResource(
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
        // persist
        setTo(to);
        setText(text);
        setFrom(from);
        setSchedule(schedule);
        setWebhookUri(webhookUri);
        setWebhookMethod(webhookMethod);
        setDeliveryReceipt(deliveryReceipt);
        setDeliveryReceiptWebhookUri(deliveryReceiptWebhookUri);
        setDeliveryReceiptWebhookMethod(deliveryReceiptWebhookMethod);
        setForwardToSms(forwardToSms);
        setForwardFromSms(forwardFromSms);
        setForwardToEmail(forwardToEmail);
        setForwardFromEmail(forwardFromEmail);
    }
    
    /** 
     Sends the specified message.

     @param message The message to send constructed using the MessageResource constructor.
     @param fdxClient
     The FoneDynamicsClient instance to use.  If null, the default instance will be used.

     @return The MessageResource that was sent, or an exception on failure.
     * @throws java.io.IOException
    */
    public static MessageResource Send(
        MessageResource message,
        FoneDynamicsClient fdxClient) throws IOException
    {
        return Send(message, fdxClient.getDefaultPropertySid(), fdxClient);
    }
        
    /** 
     Sends the specified message.

     @param message The message to send constructed using the MessageResource constructor.
     @param propertySid
     The PropertySid of the property against which to send the message.

     @param fdxClient
     The FoneDynamicsClient instance to use.  If null, the default instance will be used.

     @return The MessageResource that was sent, or an exception on failure.
     * @throws java.io.IOException
    */
    public static MessageResource Send(
        MessageResource message,
        String propertySid,
        FoneDynamicsClient fdxClient) throws IOException
    {
        // validate
        if (message == null) throw new IllegalArgumentException ("message must be provided");
        if (propertySid == null ||
            propertySid.isEmpty())
        {
            throw new IllegalArgumentException("propertySid must be provided.");                
        }

        if (message.getMessageSid() != null &&
            !message.getMessageSid().isEmpty())
        {
            throw new IllegalStateException("Cannot send an existing message.");                
        }

        // construct the request
        Request request = new Request(
            HttpMethod.Post,
            "/v2/Properties/" + propertySid + "/Messages",
            fdxClient.getAccountSid(), 
            fdxClient.getToken());

        Gson gson = new GsonBuilder().create();
        String requestJson = gson.toJson(message);
        // set the request body
        request.SetBody(requestJson, "application/json");

        // perform the request and get the response
        HttpResponse response = fdxClient.getHttpClient().Send(request);

        // throw if failed
        if (!response.getIsSuccess()) throw ErrorResponse.CreateException(response);
        String responseJson = response.GetContentString();
        
        // deserialise and return
        MessageResponse msgResponse = gson.fromJson(responseJson, MessageResponse.class);
        return msgResponse.getMessage();
     }

    /** 
        Gets a message against the account by its MessageSid.

        @param messageSid The MessageSid of the message to retrieve.
        @param fdxClient

        @return The MessageResource that was sent, or an exception on failure.
        @throws java.io.IOException
    */
    public static MessageResource Get(
            String messageSid,
            FoneDynamicsClient fdxClient)  throws IOException
        {
        // validate
        if (messageSid == null) throw new IllegalArgumentException("messageSid must be provided");

        // construct the request
        Request request = new Request(
            HttpMethod.Get,
            "/v2/Messages/" + messageSid,
            fdxClient.getAccountSid(), 
            fdxClient.getToken());

        // perform the request and get the response
        HttpResponse response = fdxClient.getHttpClient().Send(request);

        // throw if failed
        if (!response.getIsSuccess()) throw ErrorResponse.CreateException(response);
        String responseJson = response.GetContentString();
        
        Gson gson = new GsonBuilder().create();
        // deserialise and return
        MessageResponse msgResponse = gson.fromJson(responseJson, MessageResponse.class);
        return msgResponse.getMessage();
    }
        
    @Override
    public String toString() {
        return MessageSid + " - " + From + " - " + To + " - " + Direction;
    }
}