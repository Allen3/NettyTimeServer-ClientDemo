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



##Issues and Solutions

> It seems that, the browser won't get the error information which should be sent back by the server when the illegal operation occurs(e.g. using `get `request method to access non existent file). No idea about the causes, the server or the browser.

> Alright, it's my own fault cause I forget to put the send-back codes in the `sendError()` method.

----

> When request to the server with correct URL or try to access the file which is okay to be accessed, the server still will send back an Error, sometimes.

> I think it's because of the browser's behavior. Actually, when you send request to the server, especially for the first time, the browser will send a request automatically and implicitly to access the icon of the web, which is right after the request sent explicitly by user. And the request is using a HTTP `get` method, trying to get `/favicon.ico`.

 