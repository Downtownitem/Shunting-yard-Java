package shunting_yard;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class equations {

    static boolean prints = false;

    // Full Shunting yard algorithm for solving equations with +,-,*,/,(,) and ^ operators
    public static double shuntingYard(String equation) {
        equation = equation.replaceAll(" ", "");

        Queue<String> equationQueue = new LinkedList<String>();

        // Get back the postfit to the initial position
        equationQueue = postFixQueue(equation);
        // Solve the equation
        double res = stackProcess(equationQueue);

        return res;
    }


    // Just the stack process part of the algorithm, returning the result
    public static double stackProcess(Queue postFix) {
        Queue<String> equationQueue = postFix;
        Stack<String> operatorStack = new Stack<String>();

        double stack = 0;
        int cont = 1;



        // Stack process
        while (!equationQueue.isEmpty()) {
            String current = equationQueue.peek();
            boolean isOperator = current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("^");

            while (!isOperator) {
                operatorStack.add(equationQueue.poll());
                current = equationQueue.peek();
                isOperator = current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("^");
                if (prints) {
                    System.out.println(
                            "............... Step " + cont + "...............\n" +
                                    "Equation: " + equationQueue + "\n" +
                                    "Stack: " + operatorStack);
                    cont++;
                }
            }

            while (isOperator) {
                String operator = equationQueue.poll();
                if (operator.equals("+")) {
                    double result = Double.parseDouble(operatorStack.pop()) + Double.parseDouble(operatorStack.pop());
                    operatorStack.add(String.valueOf(result));
                } else if (operator.equals("-")) {
                    double num1 = Double.parseDouble(operatorStack.pop());
                    double num2 = Double.parseDouble(operatorStack.pop());
                    double result = num2 - num1;
                    operatorStack.add(String.valueOf(result));
                } else if (operator.equals("*")) {
                    double result = Double.parseDouble(operatorStack.pop()) * Double.parseDouble(operatorStack.pop());
                    operatorStack.add(String.valueOf(result));
                } else if (operator.equals("/")) {
                    double num1 = Double.parseDouble(operatorStack.pop());
                    double num2 = Double.parseDouble(operatorStack.pop());
                    double result = num2 / num1;
                    operatorStack.add(String.valueOf(result));
                } else if (operator.equals("^")) {
                    double num1 = Double.parseDouble(operatorStack.pop());
                    double num2 = Double.parseDouble(operatorStack.pop());
                    double result = Math.pow(num2, num1);
                    operatorStack.add(String.valueOf(result));
                }

                current = equationQueue.peek();
                isOperator = current != null && (current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("^"));

                if (prints) {
                    System.out.println(
                            "............... Step " + cont + "...............\n" +
                                    "Equation: " + equationQueue + "\n" +
                                    "Stack: " + operatorStack);
                    cont++;
                }
            }

            if (equationQueue.isEmpty()) {
                stack = Double.parseDouble(operatorStack.pop());
            }
        }

        if (prints) {
            System.out.println(
                    "----------------------------------------\n" +
                            "Result: " + stack + "\n" +
                            "----------------------------------------");
        }

        return stack;
    }



    // Just the post process part of the algorithm, returning a Queue
    public static Queue postFixQueue(String equation) {
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

        if (prints) {
            System.out.println("----------------------------------------\n" +
                    "Splited: " + equationQueue + "\n" +
                    "----------------------------------------");
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

            if (prints) {
                System.out.println(
                        "............... Step " + cont + "...............\n" +
                                "Equation: " + equationQueue + "\n" +
                                "Operators stack: " + operatorStack + "\n" +
                                "Postfix: " + numberQueue);
                cont++;
            }
        }

        while (!operatorStack.empty()) {
            numberQueue.add(operatorStack.pop());
            if (prints) {
                System.out.println(
                        "............... Step " + cont + "...............\n" +
                                "Equation: " + equationQueue + "\n" +
                                "Operators stack: " + operatorStack + "\n" +
                                "Postfix: " + numberQueue);
                cont++;
            }
        }

        if (prints) {
            System.out.println(
                    "------------ COMPLETE POSTFIX ---------\n" +
                            "Postfix: " + numberQueue + "\n" +
                            "----------------------------------------");
        }

        return numberQueue;
    }


    // Just the post process part of the algorithm, returning a String
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


    // Full Shunting yard algorithm for solving equations with +,-,*,/,(,) and ^ operators printing the step by step of the algorithm
    public static double shuntingYardShowProcess(String equation) {
        prints = true;
        double res = shuntingYard(equation);
        prints = false;

        return res;
    }


    // Adaptation of shunting yard algorithm for solving variable replaces in functions
    public static double shuntingYardWV(String equation, String varName, String varValue) {
        String preProcessed = preProcess(equation, varName, varValue);
        return shuntingYard(preProcessed);
    }


    // Adaptation of shunting yard algorithm for solving variable replaces in functions printing the step by step of the algorithm
    public static double shuntingYardWVWP(String equation, String varName, String varValue) {
        String preProcessed = preProcess(equation, varName, varValue);
        return shuntingYardShowProcess(preProcessed);
    }


    // Preprocess of the function for solving variable replaces in functions
    public static String preProcess(String equation, String varName, String varValue) {
        equation = equation.replace(" ", "");
        String[] equationArray = equation.split("");

        for (int i = 0; i < equationArray.length; i++) {
            if (equationArray[i].equals(varName)) {
                boolean lastIsNumber, nextIsNumber;

                try {
                    lastIsNumber = equationArray[i - 1].equals("0") || equationArray[i - 1].equals("1") || equationArray[i - 1].equals("2") || equationArray[i - 1].equals("3") || equationArray[i - 1].equals("4") || equationArray[i - 1].equals("5") || equationArray[i - 1].equals("6") || equationArray[i - 1].equals("7") || equationArray[i - 1].equals("8") || equationArray[i - 1].equals("9");
                } catch (ArrayIndexOutOfBoundsException e) {
                    lastIsNumber = false;
                }

                try {
                    nextIsNumber = equationArray[i + 1].equals("0") || equationArray[i + 1].equals("1") || equationArray[i + 1].equals("2") || equationArray[i + 1].equals("3") || equationArray[i + 1].equals("4") || equationArray[i + 1].equals("5") || equationArray[i + 1].equals("6") || equationArray[i + 1].equals("7") || equationArray[i + 1].equals("8") || equationArray[i + 1].equals("9");
                } catch (ArrayIndexOutOfBoundsException e) {
                    nextIsNumber = false;
                }

                if (lastIsNumber) {
                    equationArray[i] = equationArray[i].replace(varName, "*" + varValue);
                } else if (nextIsNumber) {
                    equationArray[i] = equationArray[i].replace(varName, varValue + "*");
                } else {
                    equationArray[i] = equationArray[i].replace(varName, varValue);
                }
            }
        }

        String newEquation = "";
        for (String s : equationArray) {
            newEquation += s;
        }

        return newEquation;
    }


    // Function to determine if the first parameter precedence is higher than the second one
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
