package shunting_yard;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class booleans {

    // Shunting yard algorithm for solving equations with <, >, !=, ==, &&, ||, (, ), +, -, *, / and ^ operators
    public static boolean shuntingYard(String equation) {
        equation = equation.replaceAll(" ", "");

        Queue<String> equations = new LinkedList<String>();
        Stack<String> operators = new Stack<String>();
        Queue<String> Postfix = new LinkedList<String>();



        return true;
    }

}
