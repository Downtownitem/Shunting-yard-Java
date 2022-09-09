import shunting_yard.equations;

public class main {

    public static void main(String[] args) {
        double res = equations.shuntingYard("1 + 2 * (3 * 2 + 4) + 2");
        System.out.println(res);


    }

}
