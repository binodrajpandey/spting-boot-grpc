package com.bebit.springbootgrpcexample.service;

import com.bebit.Greeting;
import com.bebit.HelloWorldServiceGrpc.HelloWorldServiceImplBase;
import com.bebit.User;
import com.bebit.springbootgrpcexample.repositories.UserRepository;

import javax.transaction.Transactional;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import io.grpc.stub.StreamObserver;

@GRpcService
@Transactional
public class HelloWorldServiceImpl extends HelloWorldServiceImplBase {
  @Autowired
  private UserRepository userRepository;
  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(HelloWorldServiceImpl.class);

  @Override
  public void saveUser(User request, StreamObserver<User> responseObserver) {
    LOGGER.info("Server received {}", request);
    com.bebit.springbootgrpcexample.entities.User user =
        new com.bebit.springbootgrpcexample.entities.User();
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setAddress(request.getAddress());
    userRepository.save(user);
    responseObserver.onNext(request);
    responseObserver.onCompleted();
  }
  @Override
  public void sayHello(User request,
      StreamObserver<Greeting> responseObserver) {
    LOGGER.info("server received {}", request);
    if (userRepository.findAll().isEmpty()) {
      com.bebit.springbootgrpcexample.entities.User user =
          new com.bebit.springbootgrpcexample.entities.User();
      user.setFirstName("Binod");
      user.setLastName("Pandey");
      user.setAddress("Nepal");
      userRepository.save(user);
    }
    com.bebit.springbootgrpcexample.entities.User user =
        userRepository.getOne(Long.valueOf(request.getUserId()));
    String message = new StringBuilder()
        .append("hello")
        .append(" ")
        .append(user.getFirstName())
        .append(" ")
        .append(user.getLastName())
        .append("!").toString();
    Greeting greeting =
        Greeting.newBuilder().setMessage(message).build();
    LOGGER.info("server responded {}", greeting);

    // return the Greeting
    responseObserver.onNext(greeting);
    // tell gRPC that we’ve finished writing responses.
    responseObserver.onCompleted();
    // responseObserver.onError(new Exception("unknown exception"));
  }

}
