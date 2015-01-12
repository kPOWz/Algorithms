package demo;

import java.util.Random;

import static edu.princeton.cs.introcs.StdStats.*;

/**
 * Created by karrie on 1/8/15.
 */
public class StdStats {

    private Random ran;
    public StdStats(){
        ran = new Random();
    }

    public double arrayMean(){
        int arraySize = ran.nextInt(20);
        double[] findMeanOf = new double[arraySize];
        for (int i = 0; i < findMeanOf.length; i++) {
            findMeanOf[i] = ran.nextDouble();
        }
        return mean(findMeanOf);
    }
}
