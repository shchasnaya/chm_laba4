package sample;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.mariuszgromada.math.mxparser.Argument;


import javax.swing.*;

public class Graph {
    public static void show(String expression, int n, double a, double b, int x_koshi, double y_koshi) {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series_Eyler = new XYSeries("Eyler");
        XYSeries series_Runge_Kutta = new XYSeries("Runge_Kutta");
        XYSeries series_Adams = new XYSeries("Adams");

        double x[]  = new double[n+1];
        double y[]  = new double [n+1];

        x[0] = a;
        y[x_koshi] = y_koshi;

        double h = (double)Math.round(((b-a)/n)*10d)/10d;

        Double [][]result_Eyler = new Double[n+1][2];
        result_Eyler = Eyler.method(expression, n, x, y, h);

        Double [][]result_Runge_Kutta = new Double[n+1][2];
        result_Runge_Kutta = Runge_Kutta.method(expression, n, x, y, h);

        Double [][]result_Adams = new Double[n+1][2];
        result_Adams = Adams.method(expression, n, x, y, h, a);

        int j = 0;
        for(int i=0; i < n; i++){
            series_Eyler.add(result_Eyler[i][j] , result_Eyler[i][j+1]);
        }
        dataset.addSeries(series_Eyler);

        j = 0;
        for(int i=0; i < n; i++){
            series_Adams.add(result_Adams[i][j] , result_Adams[i][j+1]);
        }
        dataset.addSeries(series_Adams);

        j = 0;
        for(int i=0; i < n; i++){
            series_Runge_Kutta.add(result_Runge_Kutta[i][j] , result_Runge_Kutta[i][j+1]);
        }
        dataset.addSeries(series_Runge_Kutta);


        //XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("function", "x", "y",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        JFrame jFrame = new JFrame("MinimalStaticChart");
        jFrame.getContentPane()
                .add(new ChartPanel(chart));
        jFrame.setSize(800, 600);
        jFrame.show();

    }

    static double f(String expression, Double variablex,Double variabley){// расчёт нашей функции первый- выражение, второй - переменная.
        String line = "x= "+variablex.toString();// Создаю стринг для аргумента.
        String line2 = "y= "+variabley.toString();//Создаю стринг для у
        Argument varx=new Argument(line);
        Argument vary= new Argument(line2);
        org.mariuszgromada.math.mxparser.Expression e = new org.mariuszgromada.math.mxparser.Expression(expression,varx,vary);
        return e.calculate() ;// вернула значение функции со значением аргумента
    }
}
