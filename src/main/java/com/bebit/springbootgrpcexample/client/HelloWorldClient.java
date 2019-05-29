package com.bebit.springbootgrpcexample.client;

import com.bebit.Greeting;
import com.bebit.HelloWorldServiceGrpc;
import com.bebit.HelloWorldServiceGrpc.HelloWorldServiceBlockingStub;
import com.bebit.Person;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class HelloWorldClient {
  private static final Logger logger = LoggerFactory.getLogger(HelloWorldClient.class);
  private HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;

  @PostConstruct
  private void init() {

    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565)
        .usePlaintext()
        .build();
    helloWorldServiceBlockingStub = HelloWorldServiceGrpc.newBlockingStub(managedChannel);

  }

  public String sayHello(String firstName, String lastName) {
    Person person = Person.newBuilder().setFirstName(firstName).setLastName(lastName).build();
    logger.info("client sending {}", person);
    Greeting greeting = helloWorldServiceBlockingStub.sayHello(person);
    logger.info("Client received message {}", greeting);
    return greeting.getMessage();
  }


}
