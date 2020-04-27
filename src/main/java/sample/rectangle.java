package sample;

import org.mariuszgromada.math.mxparser.Argument;

import static java.lang.Math.ceil;
import static java.lang.Math.pow;

/**
 * This method is used for counting by Rectangle`s method
 */

public class rectangle {
    /**
     * This method is used for counting by dichotomy method
     * @param firstfunction
     * @param firstlimit
     * @param secondlimit
     * @param eps
     * @return
     */
    public static String rectangle_method(String firstfunction, Double firstlimit, Double secondlimit,  Double eps){
        String rezult = "";
        Double h = pow(eps, 0.25);
        int n = (int)ceil((secondlimit-firstlimit)/h);

        for (int i = n; ; i++) {
            if (i % 4 == 0) {
                n = i;
                break;
            }
        }
        h = (secondlimit - firstlimit) / n;

        rezult += "Amount of parts = " + n + "\nStep = " + h + "\n";
        //  System.out.println("Answer: "+answer.toString());
        Double I =0.0;
        // метод лівих прямокутників
        for(int i = 0 ; i <= n-1; i++){

            I = I + Simpson.f(firstfunction,firstlimit + i * h);
        }
        rezult += "Rezult left->" + I * h + "\n";
        // метод правих прямокутників
        I = 0.0;
        for(int i = 0; i < n;i++){
            I = I+ Simpson.f(firstfunction,firstlimit+i*h);
        }
        rezult += "Rezult right->" + I * h + "\n";
        // метод середніх прямокутників
        I = 0.0;
        for(int i = 0; i < n-1;i++){
            I = I + Simpson.f(firstfunction,firstlimit+i*h+(h/2));
        }
        rezult += "Rezult center->" + I * h + "\n";
        return rezult;

    }
}
