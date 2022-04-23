package com.github.lette1394.oop.problems.string.calculator;

import java.util.List;
import java.util.function.Consumer;

public final class UserInput {
  private final Consumer<String> whenNumberParsed;
  private final Consumer<OperatorSign> whenOperatorParsed;

  public UserInput(Consumer<String> whenNumberParsed, Consumer<OperatorSign> whenOperatorParsed) {
    this.whenNumberParsed = whenNumberParsed;
    this.whenOperatorParsed = whenOperatorParsed;
  }

  void parse(String input) {
    List<Character> chars = input.chars()
            .mapToObj(c -> (char) c)
            .toList();
    NumberPiece numberPiece = new NumberPiece();

    for (int i = 0; i < chars.size(); i++) {
      Character ch = chars.get(i);
      boolean last = i == chars.size() - 1;

      if (OperatorSign.isSupportedOperator(ch)) {
        whenOperatorParsed.accept(OperatorSign.valueOf(ch));
      } else if (canAddNumberToCollection(numberPiece, ch)) {
        whenNumberParsed.accept(numberPiece.getNumber());
      } else if (isNumberPiece(ch)) {
        numberPiece.add(ch);
      }

      if (last && numberPiece.hasNumber()) {
        whenNumberParsed.accept(numberPiece.getNumber());
      }
    }
  }

  private boolean isNumberPiece(Character c) {
    return c >= '0' && c <= '9';
  }

  private boolean canAddNumberToCollection(NumberPiece numberPiece, char c) {
    return c == ' ' && numberPiece.hasNumber();
  }
}
