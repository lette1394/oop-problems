package com.github.lette1394.oop.problems.string.calculator.collection;

import com.github.lette1394.oop.problems.string.calculator.OperatorSign;

import java.util.Stack;

public class OperatorCollection {

  private Stack<OperatorSign> operatorSignStack = new Stack<>();

  public OperatorSign getOne() {
    return operatorSignStack.pop();
  }

  public boolean isEmpty() {
    return operatorSignStack.isEmpty();
  }

  public int size() {
    return operatorSignStack.size();
  }

  public void add(OperatorSign operatorSign) {
    operatorSignStack.add(operatorSign);
  }

  public OperatorSign peek() {
    return operatorSignStack.peek();
  }

  public void reverse() {
    Stack<OperatorSign> temp = new Stack<>();

    while (!operatorSignStack.isEmpty()) {
      OperatorSign pop = operatorSignStack.pop();
      temp.add(pop);
    }
    operatorSignStack = temp;
  }

}
