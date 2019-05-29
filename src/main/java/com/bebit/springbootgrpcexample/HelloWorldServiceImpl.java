package com.bebit.springbootgrpcexample;

import com.bebit.Greeting;
import com.bebit.HelloWorldServiceGrpc.HelloWorldServiceImplBase;
import com.bebit.Person;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;

import io.grpc.stub.StreamObserver;

@GRpcService
public class HelloWorldServiceImpl extends HelloWorldServiceImplBase {
  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(HelloWorldServiceImpl.class);

  @Override
  public void sayHello(Person request,
      StreamObserver<Greeting> responseObserver) {
    LOGGER.info("server received {}", request);
    String message = new StringBuilder()
        .append("hello")
        .append(" ")
        .append(request.getFirstName())
        .append(" ")
        .append(request.getLastName())
        .append("!").toString();
    Greeting greeting =
        Greeting.newBuilder().setMessage(message).build();
    LOGGER.info("server responded {}", greeting);

    // return the Greeting
    responseObserver.onNext(greeting);
    // tell gRPC that we’ve finished writing responses.
    responseObserver.onCompleted();
    responseObserver.onError(new Exception("unknown exception"));
  }

}
