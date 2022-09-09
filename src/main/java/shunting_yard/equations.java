package shunting_yard;

import java.util.*;

public class equations {

    //Shunting yard algorithm for solving equations with +,-,*,/,(,) and ^ operators
    public static double shuntingYard(String equation) {
        equation = equation.replaceAll(" ", "");

        Queue<String> equationQueue = new LinkedList<String>();
        Stack<String> operatorStack = new Stack<String>();
        Queue<String> numberQueue = new LinkedList<String>();

        //Split equation into list of numbers and operators
        equation = equation.replaceAll(" ", "");
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '*' || equation.charAt(i) == '/' || equation.charAt(i) == '^' || equation.charAt(i) == '(' || equation.charAt(i) == ')') {
                equationQueue.add(equation.substring(i, i + 1));
            } else {
                String number = "";
                while (i < equation.length() && equation.charAt(i) != '+' && equation.charAt(i) != '-' && equation.charAt(i) != '*' && equation.charAt(i) != '/' && equation.charAt(i) != '^' && equation.charAt(i) != '(' && equation.charAt(i) != ')') {
                    number += equation.charAt(i);
                    i++;
                }
                equationQueue.add(number);
                i--;
            }
        }

        //Convert the equation into postfix notation
        while (!equationQueue.isEmpty()) {
            String current = equationQueue.peek();
            boolean isOperator = current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("^");

            if (isOperator) {
                while (!operatorStack.empty() && isPrecedenceHigher(equationQueue.peek(), operatorStack.peek()) && !operatorStack.peek().equals("(")) {
                    numberQueue.add(operatorStack.pop());
                }
                operatorStack.add(equationQueue.poll());
            } else if (current.equals("(")) {
                operatorStack.add(equationQueue.poll());
            } else if (current.equals(")")) {
                while (!operatorStack.empty() && !operatorStack.peek().equals("(")) {
                    numberQueue.add(operatorStack.pop());
                }
                operatorStack.pop();
                equationQueue.poll();
            } else {
                numberQueue.add(equationQueue.poll());
            }
        }

        while (!operatorStack.empty()) {
            numberQueue.add(operatorStack.pop());
        }

        // Get back the postfit to the initial position
        equationQueue = numberQueue;

        double stack = 0;

        // Stack process
        while (!equationQueue.isEmpty()) {
            String current = equationQueue.peek();
            boolean isOperator = current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("^");

            while (!isOperator) {
                operatorStack.add(equationQueue.poll());
                current = equationQueue.peek();
                isOperator = current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("^");
            }

            while (isOperator) {
                String operator = equationQueue.poll();
                if (operator.equals("+")) {
                    double result = Double.parseDouble(operatorStack.pop()) + Double.parseDouble(operatorStack.pop());
                    operatorStack.add(String.valueOf(result));
                } else if (operator.equals("-")) {
                    double result = Double.parseDouble(operatorStack.pop()) - Double.parseDouble(operatorStack.pop());
                    operatorStack.add(String.valueOf(result));
                } else if (operator.equals("*")) {
                    double result = Double.parseDouble(operatorStack.pop()) * Double.parseDouble(operatorStack.pop());
                    operatorStack.add(String.valueOf(result));
                } else if (operator.equals("/")) {
                    double result = Double.parseDouble(operatorStack.pop()) / Double.parseDouble(operatorStack.pop());
                    operatorStack.add(String.valueOf(result));
                } else if (operator.equals("^")) {
                    double result = Math.pow(Double.parseDouble(operatorStack.pop()), Double.parseDouble(operatorStack.pop()));
                    operatorStack.add(String.valueOf(result));
                }

                current = equationQueue.peek();
                isOperator = current != null && (current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("^"));
            }

            if (equationQueue.isEmpty()) {
                stack = Double.parseDouble(operatorStack.pop());
            }
        }

        return stack;
    }

    public static String postProcess(String equation) {
        equation = equation.replaceAll(" ", "");

        Queue<String> equationQueue = new LinkedList<String>();
        Stack<String> operatorStack = new Stack<String>();
        Queue<String> numberQueue = new LinkedList<String>();

        //Split equation into list of numbers and operators
        equation = equation.replaceAll(" ", "");
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '*' || equation.charAt(i) == '/' || equation.charAt(i) == '^' || equation.charAt(i) == '(' || equation.charAt(i) == ')') {
                equationQueue.add(equation.substring(i, i + 1));
            } else {
                String number = "";
                while (i < equation.length() && equation.charAt(i) != '+' && equation.charAt(i) != '-' && equation.charAt(i) != '*' && equation.charAt(i) != '/' && equation.charAt(i) != '^' && equation.charAt(i) != '(' && equation.charAt(i) != ')') {
                    number += equation.charAt(i);
                    i++;
                }
                equationQueue.add(number);
                i--;
            }
        }

        //Convert the equation into postfix notation
        while (!equationQueue.isEmpty()) {
            String current = equationQueue.peek();
            boolean isOperator = current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("^");

            if (isOperator) {
                while (!operatorStack.empty() && isPrecedenceHigher(equationQueue.peek(), operatorStack.peek()) && !operatorStack.peek().equals("(")) {
                    numberQueue.add(operatorStack.pop());
                }
                operatorStack.add(equationQueue.poll());
            } else if (current.equals("(")) {
                operatorStack.add(equationQueue.poll());
            } else if (current.equals(")")) {
                while (!operatorStack.empty() && !operatorStack.peek().equals("(")) {
                    numberQueue.add(operatorStack.pop());
                }
                operatorStack.pop();
                equationQueue.poll();
            } else {
                numberQueue.add(equationQueue.poll());
            }
        }

        while (!operatorStack.empty()) {
            numberQueue.add(operatorStack.pop());
        }

        String postProcessed = "";
        while (!numberQueue.isEmpty()) {
            postProcessed = postProcessed + numberQueue.poll();
        }

        return postProcessed;
    }

    public static double shuntingYardShowProcess(String equation) {
        equation = equation.replaceAll(" ", "");

        Queue<String> equationQueue = new LinkedList<String>();
        Stack<String> operatorStack = new Stack<String>();
        Queue<String> numberQueue = new LinkedList<String>();

        //Split equation into list of numbers and operators
        int cont = 1;
        equation = equation.replaceAll(" ", "");
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '*' || equation.charAt(i) == '/' || equation.charAt(i) == '^' || equation.charAt(i) == '(' || equation.charAt(i) == ')') {
                equationQueue.add(equation.substring(i, i + 1));
            } else {
                String number = "";
                while (i < equation.length() && equation.charAt(i) != '+' && equation.charAt(i) != '-' && equation.charAt(i) != '*' && equation.charAt(i) != '/' && equation.charAt(i) != '^' && equation.charAt(i) != '(' && equation.charAt(i) != ')') {
                    number += equation.charAt(i);
                    i++;
                }
                equationQueue.add(number);
                i--;
            }
        }
        System.out.println("----------------------------------------\n" +
                "Splited: " + equationQueue + "\n" +
                "----------------------------------------");

        //Convert the equation into postfix notation
        while (!equationQueue.isEmpty()) {
            String current = equationQueue.peek();
            boolean isOperator = current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("^");

            if (isOperator) {
                while (!operatorStack.empty() && isPrecedenceHigher(equationQueue.peek(), operatorStack.peek()) && !operatorStack.peek().equals("(")) {
                    numberQueue.add(operatorStack.pop());
                }
                operatorStack.add(equationQueue.poll());
            } else if (current.equals("(")) {
                operatorStack.add(equationQueue.poll());
            } else if (current.equals(")")) {
                while (!operatorStack.empty() && !operatorStack.peek().equals("(")) {
                    numberQueue.add(operatorStack.pop());
                }
                operatorStack.pop();
                equationQueue.poll();
            } else {
                numberQueue.add(equationQueue.poll());
            }


            System.out.println(
                    "............... Step " + cont + "...............\n" +
                    "Equation: " + equationQueue + "\n" +
                    "Operators stack: " + operatorStack + "\n" +
                    "Postfix: " + numberQueue);
            cont++;
        }

        while (!operatorStack.empty()) {
            numberQueue.add(operatorStack.pop());
            System.out.println(
                    "............... Step " + cont + "...............\n" +
                    "Equation: " + equationQueue + "\n" +
                    "Operators stack: " + operatorStack + "\n" +
                    "Postfix: " + numberQueue);
            cont++;
        }

        System.out.println(
                "------------ COMPLETE POSTFIX ---------\n" +
                "Postfix: " + numberQueue + "\n" +
                "----------------------------------------");

        // Get back the postfit to the initial position
        equationQueue = numberQueue;

        double stack = 0;
        cont = 1;

        // Stack process
        while (!equationQueue.isEmpty()) {
            String current = equationQueue.peek();
            boolean isOperator = current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("^");

            while (!isOperator) {
                operatorStack.add(equationQueue.poll());
                current = equationQueue.peek();
                isOperator = current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("^");
                System.out.println(
                        "............... Step " + cont + "...............\n" +
                                "Equation: " + equationQueue + "\n" +
                                "Stack: " + operatorStack);
                cont++;
            }

            while (isOperator) {
                String operator = equationQueue.poll();
                if (operator.equals("+")) {
                    double result = Double.parseDouble(operatorStack.pop()) + Double.parseDouble(operatorStack.pop());
                    operatorStack.add(String.valueOf(result));
                } else if (operator.equals("-")) {
                    double result = Double.parseDouble(operatorStack.pop()) - Double.parseDouble(operatorStack.pop());
                    operatorStack.add(String.valueOf(result));
                } else if (operator.equals("*")) {
                    double result = Double.parseDouble(operatorStack.pop()) * Double.parseDouble(operatorStack.pop());
                    operatorStack.add(String.valueOf(result));
                } else if (operator.equals("/")) {
                    double result = Double.parseDouble(operatorStack.pop()) / Double.parseDouble(operatorStack.pop());
                    operatorStack.add(String.valueOf(result));
                } else if (operator.equals("^")) {
                    double result = Math.pow(Double.parseDouble(operatorStack.pop()), Double.parseDouble(operatorStack.pop()));
                    operatorStack.add(String.valueOf(result));
                }

                current = equationQueue.peek();
                isOperator = current != null && (current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("^"));

                System.out.println(
                        "............... Step " + cont + "...............\n" +
                                "Equation: " + equationQueue + "\n" +
                                "Stack: " + operatorStack);
                cont++;
            }

            if (equationQueue.isEmpty()) {
                stack = Double.parseDouble(operatorStack.pop());
            }
        }

        System.out.println(
                "----------------------------------------\n" +
                "Result: " + stack + "\n" +
                "----------------------------------------");

        return stack;
    }

    private static boolean isPrecedenceHigher(String op1, String op2) {
        int op1pre = 0, op2pre = 0;

        String[] precedence = {"^*/", "+-"};
        for (int i = 0; i < 2; i++) {
            if (precedence[i].contains(op1)) {
                op1pre = i + 1;
            }
            if (precedence[i].contains(op2)) {
                op2pre = i + 1;
            }
        }

        return op1pre > op2pre;
    }

}
