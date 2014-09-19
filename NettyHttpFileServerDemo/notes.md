#HTTP Protocol Development

As is based on the NIO framework, the HTTP protocol stack of Nettyis Asynchronous and non-blocking.

##HTTP request and response
Request consists of:

* A request line
* Request header fields
* (An empty line)
* An optional message body

----

Response consists of:

* A status line
* Response header fileds
* (An empty line)
* An optional message body


##Notes

1. It's necessary to transfer hard-coding characters(e.g. file separator, line separator) into the local characters.



##Problems

It seems that, the browser won't get the error information which should be sent back by the server when the illegal operation occurs(e.g. using `get `request method to access non existent file). No idea about the causes, the server or the browser.