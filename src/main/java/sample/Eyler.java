package sample;

import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;
import org.mariuszgromada.math.mxparser.Argument;

public class Eyler {

    public static String eyler(String expression, int n, double a, double b, int x_koshi, double y_koshi) {
        String rezult = "";


        //String expression = "y/2 - e^(-x)";

        //int n = 11;

        //double a = 0;
        //double b = 1;

        double x[]  = new double[n+1];
        double y[]  = new double [n+1];

        x[0] = a;
        y[x_koshi] = y_koshi;

        double h = (double)Math.round(((b-a)/n)*10d)/10d;

        Double [][]result_answer = new Double[n+1][2];

        result_answer = method(expression, n, x, y, h);

        rezult += "---------------------------------------\n";
        for(int i=0;i<n;i++){
            for(int j=0;j<2;j++){
                if(j == 0){
                    rezult +=  "|\t" + i + "\t|\t"+ result_answer[i][j]+ " \t|\t";

                }else{
                    rezult +=  result_answer[i][j]+ "    \t|";
                }
            }
            rezult += "\n";
        }
        rezult += "---------------------------------------\n";
        //result_grahh = result;
        return rezult;
    }

    public static Double[][] method(String expression, int n, double x[], double y[], double h){
        Double [][]result=new Double[n+1][2];
        int k=0;
        for(int i = 0; i < n; i++){
            x[i+1] = (double)Math.round(((x[i]+h))*10d)/10d;
            y[i+1] = (double)Math.round(((y[i]+h/2* (Graph.f(expression,x[i],y[i]) + Graph.f(expression,x[i+1],y[i]+h * Graph.f(expression,x[i],y[i])))))*1000d)/1000d;
            result[i][k]= x[i];
            result[i][k+1]= y[i];
        }
        return result;
    }
}
