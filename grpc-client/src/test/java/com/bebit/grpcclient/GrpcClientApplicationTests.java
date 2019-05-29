package com.bebit.grpcclient;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GrpcClientApplicationTests {

  @Test
  public void sayHello() {
    GrpcClientApplication client = new GrpcClientApplication();
    String message = client.sayHello("1");
    assertEquals("hello Binod Pandey!", message);
  }

}
