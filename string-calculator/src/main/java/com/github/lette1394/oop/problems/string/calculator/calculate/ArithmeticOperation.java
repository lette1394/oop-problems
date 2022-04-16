package com.github.lette1394.oop.problems.string.calculator.calculate;

import com.github.lette1394.oop.problems.string.calculator.OperatorSign;

public interface ArithmeticOperation {

  String add();

  String subtract();

  String multiply();

  default String calculateOne(OperatorSign operator) {
    String result = null;
    switch (operator) {
      case plus -> result = add();
      case subtract -> result = subtract();
      case multiply -> result = multiply();
    }
    return result;
  }
}
