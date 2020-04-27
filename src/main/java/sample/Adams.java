package sample;

import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;
import org.mariuszgromada.math.mxparser.Argument;

import static java.lang.Math.round;

public class Adams {
    public static String adams(String expression, int n, double a, double b, int x_koshi, double y_koshi) {
        double h =(double) round(((b-a)/(n-1))*10d)/10d;
        double[] x = new double[n + 1];
        double[] y = new double[n + 2];
        double[][] z = new double[n][n];
        x[0] = a;
        y[0] = 1;//Початкова умова
        Double[][] result_answer = new Double[n + 1][2];
        result_answer = method(expression, n, x, y, h, a);
        String result1 = "";
        result1 += "---------------------------------------\n";
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                if(j == 0){
                    result1 +=  "|\t" + i + "\t|\t"+ result_answer[i][j]+ " \t|\t";

                }else{
                    result1 +=  result_answer[i][j]+ "    \t|";
                }
            }
            result1 += "\n";
            }
        result1 += "---------------------------------------\n";
        return result1;
    }

    public static Double[][] method(String expression, int n, double x[], double y[], double h, double a){
        Double[][] result = new Double[n + 1][2];
        Double[][] result_1;
        result_1 = Runge_Kutta.method(expression, n, x, y, h);
        result[0][0] = a;
        result[1][0] = a+h;
        result[2][0] = a+2*h;
        result[0][1]= result_1[0][1];
        result[1][1] = result_1[1][1];
        result[2][1] = result_1[2][1];
        for(int i = 2; i <= n-1; i++){
            double ti = round ((a + i*h)*1000d)/1000d;
            result[i+1][0] = round((ti+h)*1000d)/1000d;
            result[i+1][1] = round ((result[i][1] + h/12*(23*Graph.f(expression, ti, result[i][1]) - 16*Graph.f(expression, ti-h, result[i-1][1])+ 5*Graph.f(expression, ti-2*h, result[i-2][1])))*1000d)/1000d;
        }
        return result;
    }
}
