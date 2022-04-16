package com.github.lette1394.oop.problems.string.calculator.calculate;

public class PrimitiveOperation implements ArithmeticOperation {

  private final long left;
  private final long right;

  @Override
  public String add() {
    return Long.toString(left + right);
  }

  @Override
  public String subtract() {
    return Long.toString(left - right);
  }

  @Override
  public String multiply() {
    return Long.toString(left * right);
  }

  public PrimitiveOperation(String leftValue, String rightValue) {
    left = Long.parseLong(leftValue);
    right = Long.parseLong(rightValue);
  }
}
