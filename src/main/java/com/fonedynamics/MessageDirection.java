package com.fonedynamics;

/** 
 Represents the direction of a message.
*/
public enum MessageDirection
{
    /** 
     The message is an outgoing message.
    */
    Transmit,
    /** 
     The message is an incoming message.
    */
    Receive;

    /**
     * The size
     */
    public static final int SIZE = java.lang.Integer.SIZE;

    /**
     * Returns int value for given enum
     * @return int value for given enum
     */
    public int getValue()
    {
        return this.ordinal();
    }

    /**
     * Returns MessageDirection enum for given string
     * @param value The int value
     * @return the enum
     */
    public static MessageDirection forValue(int value)
    {
        return values()[value];
    }
}
