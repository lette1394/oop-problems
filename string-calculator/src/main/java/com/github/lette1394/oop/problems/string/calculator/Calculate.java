package com.github.lette1394.oop.problems.string.calculator;

import com.github.lette1394.oop.problems.string.calculator.calculate.ArithmeticOperation;
import com.github.lette1394.oop.problems.string.calculator.calculate.OperationFactory;


public class Calculate {
  private final OperationFactory operationFactory;

  public Calculate(OperationFactory calculateFactory) {
    this.operationFactory = calculateFactory;
  }

  public String one(String leftValue, String rightValue, OperatorSign operatorSign) {
    ArithmeticOperation operation = operationFactory.create(leftValue, rightValue);
    return operation.calculateOne(operatorSign);
  }

}
