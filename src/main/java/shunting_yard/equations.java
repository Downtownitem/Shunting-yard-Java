package Hunting_yard;

import java.util.ArrayList;

public class equations {

    //Hunting yard algorithm for solving equations with +,-,*,/,(,) and ^ operators
    public static double huntingYard(String equation) {
        equation = equation.replaceAll(" ", "");

        ArrayList<String> equationList = new ArrayList<String>();
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '*' || equation.charAt(i) == '/' || equation.charAt(i) == '(' || equation.charAt(i) == ')' || equation.charAt(i) == '^') {
                equationList.add(Character.toString(equation.charAt(i)));
            } else {
                String number = "";
                while (i < equation.length() && equation.charAt(i) != '+' && equation.charAt(i) != '-' && equation.charAt(i) != '*' && equation.charAt(i) != '/' && equation.charAt(i) != '(' && equation.charAt(i) != ')' && equation.charAt(i) != '^') {
                    number += equation.charAt(i);
                    i++;
                }
                i--;
                equationList.add(number);
            }
        }

        ArrayList<String> numbers = new ArrayList<String>();
        ArrayList<String> operators = new ArrayList<String>();

        boolean switcher = false;

        for (int i = 0; i < equationList.size(); i++) {
            if (equationList.get(i).equals("+") || equationList.get(i).equals("-") || equationList.get(i).equals("*") || equationList.get(i).equals("/") || equationList.get(i).equals("^") || equationList.get(i).equals("(")) {
                operators.add(equationList.get(i));

            } else if (equation.equals(")")) {
                

            }
        }


    }

}
