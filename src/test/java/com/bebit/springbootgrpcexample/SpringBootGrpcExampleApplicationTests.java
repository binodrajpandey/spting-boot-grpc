package com.bebit.springbootgrpcexample;


import static org.junit.Assert.assertEquals;

import com.bebit.springbootgrpcexample.client.HelloWorldClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootGrpcExampleApplicationTests {
  @Autowired
  private HelloWorldClient helloWorldClient;

  @Test
  public void sayHello() {
    String message = helloWorldClient.sayHello("1");
    assertEquals("hello Binod Pandey!", message);
  }

}
