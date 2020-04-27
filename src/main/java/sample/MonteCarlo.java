package sample;

import org.mariuszgromada.math.mxparser.Argument;

import static java.lang.Math.ceil;
import static java.lang.Math.pow;

/**
 * This method is used for counting by MonteCarlo`s method
 */
public class MonteCarlo {
    /**
     * This method is used for counting by dichotomy method
     * @param firstfunction
     * @param firstlimit
     * @param secondlimit
     * @param eps
     * @return
     */
    public static String MonteCarlo(String firstfunction, Double firstlimit, Double secondlimit,  Double eps){
        String rezult = "";
        Double h = pow(eps,0.25);
        int n = (int)ceil((secondlimit-firstlimit)/h);
        for (int i=n;;i++){
            if(i%4==0) {n = i;
                break;}
        }
        int nMonteCarlo=n*10000;//Количество ітераций, из-за большого количества, оно может подвисать
        rezult += "Amount of parts = "+ n + "\n";
        Double rez= Double.valueOf(0);

        for (int i=0;i<nMonteCarlo-1;i++){
            rez=(rez+Simpson.f(firstfunction,rnd(firstlimit,secondlimit)));
        }
        rez*=(secondlimit-firstlimit)/nMonteCarlo;
        rezult += "Answer: "+ rez + "\n";
        return  rezult;
    }

    public static Double rnd(Double min, Double max) {
        return (Math.random() * (max-min)) + min;
    }
}
