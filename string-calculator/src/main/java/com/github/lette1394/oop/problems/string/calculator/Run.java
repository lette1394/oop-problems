package com.github.lette1394.oop.problems.string.calculator;

import com.github.lette1394.oop.problems.string.calculator.calculate.OperationFactory;
import com.github.lette1394.oop.problems.string.calculator.collection.NumberCollection;
import com.github.lette1394.oop.problems.string.calculator.collection.OperatorCollection;

import java.util.function.Consumer;

public class Run {

  private final NumberCollection numberCollection = new NumberCollection();
  private final OperatorCollection operatorCollection = new OperatorCollection();
  private final NumberPiece numberPiece = new NumberPiece();
  private final Calculate calculate;

  public Run() {
    this.calculate = new Calculate(new OperationFactory());
  }

  public String calculate(String input) {
    Consumer<String> whenNumberParsed = parsedNumber -> {
      numberCollection.add(parsedNumber);
      if (existHighOperatorSign()) {
        addNumber();
      }
    };
    Consumer<OperatorSign> whenOperatorParsed = operatorSign -> {
      operatorCollection.add(operatorSign);
    };
    UserInput userInput = new UserInput(whenNumberParsed, whenOperatorParsed);

    userInput.parse(input);
    return getResult();
  }

  private String getResult() {
    numberCollection.reverse();
    operatorCollection.reverse();

    while (numberCollection.size() > 1) {
      addNumber();
    }

    return numberCollection.getOne();
  }

  private void addNumber() {
    String leftValue = numberCollection.getOne();
    String rightValue = numberCollection.getOne();
    OperatorSign operatorSign = operatorCollection.getOne();
    String result = calculate.one(leftValue, rightValue, operatorSign);
    numberCollection.add(result);
  }

  private boolean existHighOperatorSign() {
    if (operatorCollection.isEmpty()) {
      return false;
    }

    if (operatorCollection.size() >= numberCollection.size()) {
      return false;
    }

    OperatorSign lastOperator = operatorCollection.peek();
    return lastOperator == OperatorSign.divide || lastOperator == OperatorSign.multiply;
  }
}
