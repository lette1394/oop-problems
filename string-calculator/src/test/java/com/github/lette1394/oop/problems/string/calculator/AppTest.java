package com.github.lette1394.oop.problems.string.calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

  private SimulatedConsole console;

  @BeforeEach
  void beforeEach() throws IOException {
    console = new SimulatedConsole();
  }

  @Test
  void digit() throws Exception {
    console.type("1");
    console.typeEnter();

    App.main(null);

    var actual = console.output();
    assertEquals("num : 1\n", actual);
  }

  @Test
  void binary() throws Exception {
    console.type("1 + 2");
    console.typeEnter();

    App.main(null);

    var actual = console.output();
    assertEquals("num : 3\n", actual);
  }

  @Test
  void simple() throws Exception {
    console.type("1 + 2 * 3 - 4");
    console.typeEnter();

    App.main(null);

    var actual = console.output();
    assertEquals("num : 3\n", actual);
  }

  @Test
  void complex() throws Exception {
    console.type("32435456436754325674356756762221212798");
    console.type(" - ");
    console.type("342283024803781287013");
    console.type(" * ");
    console.type("235465789800876543223456543454");
    console.type(" + ");
    console.type("12341251246");
    console.type(" - ");
    console.type("3453476564534");
    console.type(" + ");
    console.type("13476857565743");
    console.type(" * ");
    console.type("1234567");
    console.typeEnter();

    App.main(null);

    var actual = console.output();
    assertEquals("num : -80595942770822941182435736008003954206997027825111\n", actual);
  }

  @AfterEach
  void afterEach() throws IOException {
    console.close();
  }
}
