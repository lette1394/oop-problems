package com.github.lette1394.oop.problems.string.calculator;

import com.github.lette1394.oop.problems.string.calculator.calculate.OperationFactory;
import com.github.lette1394.oop.problems.string.calculator.collection.NumberCollection;
import com.github.lette1394.oop.problems.string.calculator.collection.OperatorCollection;

import java.util.List;

public class Run {

  private final NumberCollection numberCollection = new NumberCollection();
  private final OperatorCollection operatorCollection = new OperatorCollection();
  private final NumberPiece numberPiece = new NumberPiece();
  private final Calculate calculate;

  public Run() {
    this.calculate = new Calculate(new OperationFactory());
  }

  public String calculate(String input) {
    List<Character> chars = input.chars()
            .mapToObj(c -> (char) c)
            .toList();

    for (int i = 0; i < chars.size(); i++) {
      Character ch = chars.get(i);
      boolean last = i == chars.size() - 1;

      if (existHighOperatorSign()) {
        addNumber();
      }
      parse(ch, last);
    }
    checkLast();
    return getResult();
  }

  private void checkLast() {
    if (existHighOperatorSign()) {
      addNumber();
    }
  }

  private String getResult() {
    numberCollection.reverse();
    operatorCollection.reverse();

    while (numberCollection.size() > 1) {
      addNumber();
    }

    return numberCollection.getOne();
  }

  private void parse(Character c, boolean last) {
    if (OperatorSign.isSupportedOperator(c)) {
      operatorCollection.add(OperatorSign.valueOf(c));
    }

    if (canAddNumberToCollection(c)) {
      numberCollection.add(numberPiece.getNumber());
    }

    if (isNumberPiece(c)) {
      numberPiece.add(c);
    }

    if (last) {
      if (numberPiece.hasNumber()) {
        numberCollection.add(numberPiece.getNumber());
      }
    }
  }

  private boolean isNumberPiece(Character c) {
    return c >= '0' && c <= '9';
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

  private boolean canAddNumberToCollection(char c) {
    return c == ' ' && numberPiece.hasNumber();
  }
}
