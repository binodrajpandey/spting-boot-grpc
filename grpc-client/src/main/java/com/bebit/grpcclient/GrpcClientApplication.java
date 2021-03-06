package com.bebit.grpcclient;


import com.bebit.Greeting;
import com.bebit.HelloWorldServiceGrpc;
import com.bebit.HelloWorldServiceGrpc.HelloWorldServiceBlockingStub;
import com.bebit.User;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

// @Component
public class GrpcClientApplication {
  private static final Logger logger = LoggerFactory.getLogger(GrpcClientApplication.class);
  private HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;

  @PostConstruct
  private void init() {
    //
    // ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565)
    // .usePlaintext()
    // .build();
    // helloWorldServiceBlockingStub = HelloWorldServiceGrpc.newBlockingStub(managedChannel);

  }

  public String sayHello(String id) {
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565)
        .usePlaintext()
        .build();
    helloWorldServiceBlockingStub = HelloWorldServiceGrpc.newBlockingStub(managedChannel);
    User person = User.newBuilder().setUserId(id).build();
    logger.info("client sending {}", person);
    Greeting greeting = helloWorldServiceBlockingStub.sayHello(person);
    logger.info("Client received message {}", greeting);
    return greeting.getMessage();
  }


}
