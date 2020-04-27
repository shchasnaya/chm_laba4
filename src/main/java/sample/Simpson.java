package sample;

import org.mariuszgromada.math.mxparser.Argument;

import static java.lang.Math.ceil;
import static java.lang.Math.pow;

/**
 * This method is used for counting by Simpson`s method
 */

public class Simpson {

    /**
     * This method is used for counting by dichotomy method
     * @param firstfunction
     * @param a
     * @param b
     * @param eps
     * @return
     */
    public static String Simpson_method(String firstfunction, Double a, Double b, Double eps){
        String rezult = "";
        Double h = pow(eps, 0.25);
        int n = (int)ceil((b-a)/h);

        for (int i = n; ; i++) {
            if (i % 4 == 0) {
                n = i;
                break;
            }
        }
        h = (b - a) / n;

        rezult += "Amount of parts = " + n + "\nStep = " + h + "\n";

        Double s1 = 0.0;
        Double s2 = 0.0;
        Double S;
        for(int i = 1; i <= n; i++){
            if(i % 2 == 0){
                s2 = s2 + f(firstfunction, a+i*h);
            }else{
                s1 = s1 + f(firstfunction, a+i*h);
            }
        }
        S = (((((f(firstfunction, a) - f(firstfunction, b))/2 + 2*s1 + s2))/3)*2*h);
        rezult += "Result 1-> " + S + "\n";

        Double s = 0.0;
        Double xi1;
        Double xi;

        Double S2 = 0.0;
        for(int i = 0; i <= n-1; i++){
            xi1 = a+i*h;
            xi = xi1 + h;
            s = s + f(firstfunction, xi1) + 4*f(firstfunction, (xi1+xi)/2) + f(firstfunction, xi);
        }
        S2 = s*h/6;
        rezult += "Result 2-> " + S2;
        return rezult;
    }

    /**
     * This method is used to calculate our function
     * @param expression
     * @param variable
     * @return
     */

    static double f(String expression, Double variable) {
        String line = "x= " + variable.toString();
        Argument var = new Argument(line);
        org.mariuszgromada.math.mxparser.Expression e = new org.mariuszgromada.math.mxparser.Expression(expression, var);
        return e.calculate();
    }
}
