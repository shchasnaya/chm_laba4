package sample;

import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;
import org.mariuszgromada.math.mxparser.Argument;

import static java.lang.Math.ceil;
import static java.lang.Math.pow;

/**
 * This method is used for counting by Trapezoidal method
 */

public class Trapezoidal {
    /**
     * This method is used for counting by dichotomy method
     * @param function
     * @param a
     * @param b
     * @param eps
     * @return
     */
    public static String method(String function, double a, double b, double eps) {
        Variable xVar = new Variable("x");
        edu.hws.jcm.data.Parser parser = new edu.hws.jcm.data.Parser(Parser.STANDARD_FUNCTIONS);
        parser.add(xVar);
        edu.hws.jcm.data.Expression expr = parser.parse(function);
        System.out.println(expr.derivative(xVar));
        double x;
        double h = pow(eps, 0.25);
        int n = (int) ceil((b-a) / h);
        for (int i = n; ; i++) {
            if (i % 4 == 0) {
                n = i;
                break;
            }
        }
        h = (b - a) / n;
        double I = 0.0;
        for (int i = 1; i < n; i++) {
            x = (a + i * h);
            I = I + Simpson.f(function, x);
        }
        I = h / 2 * (Simpson.f(function, a) + Simpson.f(function, b) + 2 * I);
        return "Amount of parts = " + n + "\nStep = " + h + "\nResult =  " + I;
    }
}
