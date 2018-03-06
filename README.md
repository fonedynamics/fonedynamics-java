# Fone Dynamics Client Library for Java

JDK 1.7 client library for [Fone Dynamics](https://www.fonedynamics.com/) services.  This library currently supports messaging only.

## Installing the Fone Dynamics Client Library for Java

The client library is available via the Maven Central Repository (https://repo.maven.apache.org/maven2/com/fonedynamics/fonedynamics-apiclient/).

Please add the following dependency to your pom.xml file (version number should usually be the latest available):

<dependency>
    <groupId>com.fonedynamics</groupId>
    <artifactId>fonedynamics-apiclient</artifactId>
    <version>1.3</version>
    <type>jar</type>
</dependency>

## Authentication with FoneDynamicsClient

You can define your authentication credentials on startup of your application by initializing `FoneDynamicsClient` as below. This will statically initialize authentication for the entire application.

```java
// initialize the Fone Dynamics client library
FoneDynamicsClient fdxClient = new FoneDynamicsClient(
    "YOUR_ACCOUNT_SID", 
    "YOUR_TOKEN",
    "OPTIONAL_DEFAULT_PROPERTY_SID");
```

Alternatively, if you need to interact with multiple Fone Dynamics accounts, you can construct an instance of `FoneDynamicsClient` for a specific account and explicitly pass this as an argument when interacting with the client library.

## Usage

### Sending a message

To send a message, create it first via either the various MessageResource constructors or use the MessageResource builder.
Then use `MessageResource.send()`.  The response will contain details of the message that has been sent.

```java
MessageResourceBuilder builder = new MessageResourceBuilder();
            
MessageResource msgToSend = builder
    .setTo("+61499999999")
    .setText("Hello world!")
    .createMessageResource();

// send the message.
MessageResource msg = MessageResource.send(msgToSend, fdxClient);

// log details of sent message to console
System.out.printf("Sent message with MessageSid: %s From: %s To: %s Text: %s"
                    ,msg.getMessageSid()
                    ,msg.getFrom()
                    ,msg.getTo()
                    ,msg.getText());
```

### Getting a message

To get the details of a message that has been sent or received, use `MessageResource.get()`.

```java
// get a message by its MessageSid
String messageSid = "MGTBUEHNLFOOHNSIOCSAQRRUKLMEZSLE";
MessageResource msg = MessageResource.get(messageSid);

// log details of message to console
System.out.printf("Fetched message. MessageSid: %s From: %s To: %s Text: %s"
                    ,msg.getMessageSid()
                    ,msg.getFrom()
                    ,msg.getTo()
                    ,msg.getText());
```

### Batch sending messages

To send a batch of messages, use the overload of `MessageResource.send()` that accepts an `IEnumerable<MessageResource>`.  Individual messages within the batch can succeed or fail. The return value is a list of messages that have been sent and failed, and you can determine whether a message succeeded by checking the `Successful` property against a message in the return list.

```java
// construct and messages
ArrayList<MessageResource> messages = new ArrayList<MessageResource>();

MessageResource msg1 = new MessageResource("+61499999999", "FDX", "and a one");
MessageResource msg2 = new MessageResource("+61499999999", "FDX", "and a two");
MessageResource msg3 = new MessageResource("+61499999999", "FDX", "and a one, two, three!");

messages.add(msg1);
messages.add(msg2);
messages.add(msg3);

 List<BatchMessageResource> result = BatchMessageResource.send(messages, fdxClient);

// write responses to console
for (int i = 0; i < result.size(); i++)
{
    BatchMessageResource response = result.get(i);
    if(response.getSuccessful())
    {
        System.out.printf("Message #%d successful! MessageSid: %s%n", i, response.getMessageSid());
    }
    else
    {
        System.out.printf("Message #%d failed! %s: %s%n", i, response.getErrorCode(), response.getErrorMessage());
    }
}
```

## Documentation

JavaDoc documentation is included for all methods and parameters.

For additional documentation, please see the [Fone Dynamics REST API Documentation](https://www.fonedynamics.com/docs/rest-api).
