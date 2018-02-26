package com.fonedynamics;

import java.util.ArrayList;
import java.util.List;

/** 
 * The request to a BatchMessage.
*/
public class BatchMessageRequest
{
    // The messages to be sent.
    private List<MessageResource> Messages;

    /**
     * The messages to be sent.
     * @return The messages to be sent
    */
    public final List<MessageResource> getMessages()
    {
        return Messages;
    }

    // Set messages to be sent
    private void setMessages(Iterable<MessageResource> value)
    {
        Messages = new ArrayList();
        
        for (MessageResource msg: value) {
            Messages.add(msg);
        }
    }

    /** 
     * Constructs a new BatchMessageRequest with the specified messages.
     * @param messages The messages to be sent
    */
    public BatchMessageRequest(Iterable<MessageResource> messages)
    {
        // persist
        setMessages(messages);
    }
}