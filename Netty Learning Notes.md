#Pro-Netty Notes

##Introduction to NIO

Network coding is based on the model of "Client/Server" Mode, which indicates that communication between two threads.

The difference between `Synchronous I/O` and `Asynchronous I/O` appears on the threads' behaviors. for `Synchronous I/O`, mostly, it blocks the thread when the input request was sent. Therefore, a great amount of threads are in need for high load, high concurrency occasion. While `Asynchronous I/O` solves such problem, by maintaining "buffer" and "channel" and "selector" so that only one thread is enough to respond to all requests sent by clients.