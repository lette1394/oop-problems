package com.github.lette1394.oop.problems.string.calculator;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SimulatedConsole implements Closeable {
  private final PipedOutputStream inputs;
  private final ByteArrayOutputStream outputs;

  public SimulatedConsole() throws IOException {
    inputs = new PipedOutputStream();
    System.setIn(new PipedInputStream(inputs));

    outputs = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outputs);
    System.setOut(out);
  }

  public void type(String input) throws IOException {
    inputs.write(input.getBytes(UTF_8), 0, input.length());
    inputs.flush();
  }

  public void typeEnter() throws IOException {
    var enter = System.lineSeparator();
    inputs.write(enter.getBytes(UTF_8), 0, enter.length());
    inputs.flush();
  }

  public String output() {
    return outputs.toString(UTF_8);
  }

  @Override
  public void close() throws IOException {
    inputs.close();
  }
}
