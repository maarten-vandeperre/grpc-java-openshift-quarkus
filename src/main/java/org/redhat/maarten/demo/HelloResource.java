package org.redhat.maarten.demo;

import io.quarkus.grpc.GrpcClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.time.Duration;

@Path("/hello")
public class HelloResource {

    @GrpcClient("production")
    HelloGrpc helloGrpc;

    @GET
    public String hello() {
        HelloReply reply = helloGrpc
                .sayHello(HelloRequest.newBuilder().setName("test").build()).await().atMost(Duration.ofSeconds(50));
        return reply.getMessage();
    }
}
