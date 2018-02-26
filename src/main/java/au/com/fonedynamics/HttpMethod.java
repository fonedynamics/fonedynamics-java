package au.com.fonedynamics;

/** 
 Describes a HTTP method.
*/
enum HttpMethod
{
    Get,
    Post,
    Put,
    Delete;

    static final int SIZE = java.lang.Integer.SIZE;

    int getValue()
    {
        return this.ordinal();
    }

    static HttpMethod forValue(int value)
    {
        return values()[value];
    }
}