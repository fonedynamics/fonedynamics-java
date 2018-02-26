package com.fonedynamics;

/** 
 The method to use for webhooks.
*/
public enum WebhookMethod
{
    /** 
     Webhooks should use POST.
    */
    Post,
    /** 
     Webhooks should use GET.
    */
    Get;

    static final int SIZE = java.lang.Integer.SIZE;

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
    public WebhookMethod forValue(int value)
    {
        return values()[value];
    }
}