package com.tsystems.javaschool.tasks.calculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        if (statement == "" || statement == null) {
            System.out.println("Empty field of null");
            return null;
        }
        if (!checkStatement(statement)) {
            return null;
        }
        statement = reversePolishNotation(statement);

        return calculate(statement);
    }

    Boolean checkStatement(String statement) {
        boolean isCorrect = true;
        char[] symbols = statement.toCharArray();
        if (symbols[0] == '+' || symbols[0] == '-' || symbols[0] == '*' || symbols[0] == '/' || symbols[0] == ')') {
            isCorrect = false;
        }
        if (statement == "") {
            isCorrect = false;
        }
        return isCorrect;
    }

    //строим строку на основе обратной польской записи

    private String reversePolishNotation(String statement) {
        StringBuilder strblStck = new StringBuilder("");
        StringBuilder rpnOutStatement = new StringBuilder("");
        char symbOfStatement;
        char tmpSymb;

        for (int i = 0; i < statement.length(); i++) {
            symbOfStatement = statement.charAt(i);
            if (isItOperation(symbOfStatement)) {
                while (strblStck.length() > 0) {
                    tmpSymb = strblStck.substring(strblStck.length() - 1).charAt(0);
                    if (isItOperation(tmpSymb) && (operationPriority(symbOfStatement) >= operationPriority(tmpSymb))) {
                        rpnOutStatement.append(" ").append(tmpSymb).append(" ");
                        strblStck.setLength(strblStck.length() - 1);
                    } else {
                        rpnOutStatement.append(" ");
                        break;
                    }
                }
                rpnOutStatement.append(" ");
                strblStck.append(symbOfStatement);
            } else if ('(' == symbOfStatement) {
                strblStck.append(symbOfStatement);
            } else if (')' == symbOfStatement) {
                tmpSymb = strblStck.substring(strblStck.length() - 1).charAt(0);
                while ('(' != tmpSymb) {
                    rpnOutStatement.append(" ").append(tmpSymb);
                    strblStck.setLength(strblStck.length() - 1);
                    tmpSymb = strblStck.substring(strblStck.length() - 1).charAt(0);
                }
                strblStck.setLength(strblStck.length() - 1);
            } else {
                rpnOutStatement.append(symbOfStatement);
            }
        }
        while (strblStck.length() > 0) {
            rpnOutStatement.append(" ").append(strblStck.substring(strblStck.length() - 1));
            strblStck.setLength(strblStck.length() - 1);
        }
        System.out.println(rpnOutStatement);
        return rpnOutStatement.toString();
    }

    private boolean isItOperation(char symbOfStatement) {
        switch (symbOfStatement) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
        }
        return false;
    }

    private int operationPriority(char symb) {
        switch (symb) {
            case '/':
            case '*':
                return 1;
        }
        return 2;
    }

    public String calculate(String statement) {
        String outputResult;
        String rPNElement;
        double a;
        double b;
        double d;
        StringTokenizer stringTokenizer = new StringTokenizer(statement);
        Deque<Double> deque = new ArrayDeque<>();

        while (stringTokenizer.hasMoreTokens()) {
            try {
                rPNElement = stringTokenizer.nextToken().trim();
                if (1 == rPNElement.length() && isItOperation(rPNElement.charAt(0))) {
                    b = deque.pop();
                    a = deque.pop();

                    switch (rPNElement.charAt(0)) {
                        case '+':
                            a += b;
                            break;
                        case '-':
                            a -= b;
                            break;
                        case '/':
                            if (b == 0) {
                                return null;
                            }
                            a /= b;
                            break;
                        case '*':
                            a *= b;
                            break;
                    }
                    deque.push(a);
                } else {
                    a = Double.parseDouble(rPNElement);
                    deque.push(a);
                }
            } catch (Exception e) {
                System.out.println("Incorrect symbol in line");
                return null;
            }
        }
        d = deque.pop();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.####", dfs);
        outputResult = df.format(d);
        return outputResult;
    }
}