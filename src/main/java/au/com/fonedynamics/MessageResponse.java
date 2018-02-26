package au.com.fonedynamics;

/** 
 The response to a Message request.
*/
public class MessageResponse {
    // The account secure identifier associated with the message.
    private MessageResource Message;

    /** 
     Get the message that was sent.
     * @return The message that was sent.
    */
    public final MessageResource getMessage()
    {
        return Message;
    }
    final void setMessage(MessageResource value)
    {
        Message = value;
    }
}
