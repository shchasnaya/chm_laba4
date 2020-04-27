package sample;

import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;

public class Runge_Kutta {
    public static String Runge_Kutta(String expression, int n, double a, double b, int x_koshi, double y_koshi){
        String rezult = "";
        double h =(double)Math.round(((b-a)/(n-1))*10d)/10d;//округление, умножаем на такое число, сколько знаков нужно после запятой
        double[]x= new double[n+1];
        double[]y = new double[n+1];
        x[0]=a;
        y[x_koshi]= y_koshi;//Початкова умова
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
        return rezult;
    }

    public static Double[][] method(String expression, int n, double x[], double y[], double h){
        Double [][]result=new Double[n+1][2];
        int k=0;
        Double k1,k2,k3,k4;
        for (int i=0;i<n;i++){
            x[i+1]=(double)Math.round((x[i]+h)*10d)/10d;
            k1=(double)Math.round(Graph.f(expression,x[i],y[i])*1000d)/1000d;
            k2=(double)Math.round(Graph.f(expression,x[i]+(h/2),y[i]+((h*k1)/2))*1000d)/1000d;
            k3=(double)Math.round(Graph.f(expression,x[i]+(h/2),y[i]+((h*k2)/2))*1000d)/1000d;
            k4=(double)Math.round(Graph.f(expression,x[i]+h,    y[i]+h*(k3))*1000d)/1000d;
            y[i+1]=(double)Math.round((y[i]+(h*((k1+(2*k2)+(2*k3)+k4)/6)))*1000d)/1000d;

            result[i][k]= x[i];
            result[i][k+1]= y[i];
        }
        return result;
    }

    //public static String Runge_Kutta
}
