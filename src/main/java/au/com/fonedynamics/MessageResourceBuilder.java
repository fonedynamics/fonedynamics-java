package au.com.fonedynamics;

/**
 * Builds a MessageResource instance
 */
public class MessageResourceBuilder {

    private String to;
    private String text;
    private String from;
    private Long schedule;
    private String webhookUri;
    private WebhookMethod webhookMethod;
    private boolean deliveryReceipt;
    private String deliveryReceiptWebhookUri;
    private WebhookMethod deliveryReceiptWebhookMethod;
    private String forwardToSms;
    private String forwardFromSms;
    private String forwardToEmail;
    private String forwardFromEmail;

    /**
     * Constructs a MessageResourceBuilder
    */
    public MessageResourceBuilder() {
    }

     /** 
     Set the E164 formatted recipient of the SMS message.
     * @param to The E164 formatted recipient of the SMS message.
     * @return The builder instance
    */
    public MessageResourceBuilder setTo(String to) {
        this.to = to;
        return this;
    }

    /** 
     Set the content of the SMS message.
     * @param text The content of the SMS message.
     * @return The builder instance
    */
    public MessageResourceBuilder setText(String text) {
        this.text = text;
        return this;
    }

    /** 
     Get the alphanumeric or E164 formatted sender ID of the message.
     If composing a message, a null or empty value indicates that a number should
     be dynamically leased for this message.
     * @param from The alphanumeric or E164 formatted sender ID of the message.
     * @return The builder instance
    */
    public MessageResourceBuilder setFrom(String from) {
        this.from = from;
        return this;
    }

    /** 
     Get the UTC time in seconds since Unix Epoch to send the message. This can be at most
     14 days in the future. If the value is in the past, the request will fail, unless
     the value is less than 1 hour in the past, in which case the request will succeed
     and the message will be sent immediately.
     * @param schedule The UTC time in seconds since Unix Epoch to send the message.
     * @return The builder instance
    */
    public MessageResourceBuilder setSchedule(Long schedule) {
        this.schedule = schedule;
        return this;
    }

    /** 
     Get the callback URI to invoke when a delivery receipt is received. Note that
     DeliveryReceipt must be set to true for this to be triggered.
     * @param webhookUri The callback URI to invoke when a delivery receipt is received
     * @return The builder instance
    */
    public MessageResourceBuilder setWebhookUri(String webhookUri) {
        this.webhookUri = webhookUri;
        return this;
    }

    /** 
     Get the method to use for delivery receipt callbacks. Valid options are POST and GET.
     Default is POST.
     * @param webhookMethod the method to use for delivery receipt callbacks
     * @return The builder instance
    */
    public MessageResourceBuilder setWebhookMethod(WebhookMethod webhookMethod) {
        this.webhookMethod = webhookMethod;
        return this;
    }

    /** 
     Set to true if a delivery receipt was requested and false otherwise.
     * @param deliveryReceipt Set to true if a delivery receipt was requested and false otherwise.
     * @return The builder instance
    */
    public MessageResourceBuilder setDeliveryReceipt(boolean deliveryReceipt) {
        this.deliveryReceipt = deliveryReceipt;
        return this;
    }

    /** 
     Get the callback URI to invoke when a delivery receipt is received. Note that
     DeliveryReceipt must be set to true for this to be triggered.
     * @param deliveryReceiptWebhookUri The callback URI to invoke when a delivery receipt is received
     * @return The builder instance
    */
    public MessageResourceBuilder setDeliveryReceiptWebhookUri(String deliveryReceiptWebhookUri) {
        this.deliveryReceiptWebhookUri = deliveryReceiptWebhookUri;
        return this;
    }

    /** 
     Get the method to use for delivery receipt callbacks. Valid options are POST and GET.
     Default is POST.
     * @param deliveryReceiptWebhookMethod the method to use for delivery receipt callbacks
     * @return The builder instance
    */
    public MessageResourceBuilder setDeliveryReceiptWebhookMethod(WebhookMethod deliveryReceiptWebhookMethod) {
        this.deliveryReceiptWebhookMethod = deliveryReceiptWebhookMethod;
        return this;
    }

    /** 
     When a response is received, forward it to this number in E164 format.
     * @param forwardToSms Forwarding number
     * @return The builder instance
    */
    public MessageResourceBuilder setForwardToSms(String forwardToSms) {
        this.forwardToSms = forwardToSms;
        return this;
    }

    /** 
     When forwarding via SMS, send from this E164 formatted number or alphanumeric sender
     ID. By default the sender ID will be the sender ID of the responding party.
     * @param forwardFromSms Sent from number
     * @return The builder instance
    */
    public MessageResourceBuilder setForwardFromSms(String forwardFromSms) {
        this.forwardFromSms = forwardFromSms;
        return this;
    }

    /** 
     When a response is received, forward it over email to this email address.
     * @param forwardToEmail Forwarding email
     * @return The builder instance
    */
    public MessageResourceBuilder setForwardToEmail(String forwardToEmail) {
        this.forwardToEmail = forwardToEmail;
        return this;
    }

/** 
     When forwarding via email, send from this email address. By default this is a
     "no reply" email address.
     * @param forwardFromEmail sent from email address
     * @return The builder instance
    */    
    public MessageResourceBuilder setForwardFromEmail(String forwardFromEmail) {
        this.forwardFromEmail = forwardFromEmail;
        return this;
    }

    /**
     * Creates and returns a message resource instance
     * @return the created message return instance
     */
    public MessageResource createMessageResource() {
        return new MessageResource(to, text, from, schedule, webhookUri, webhookMethod, deliveryReceipt, deliveryReceiptWebhookUri, deliveryReceiptWebhookMethod, forwardToSms, forwardFromSms, forwardToEmail, forwardFromEmail);
    }
}
