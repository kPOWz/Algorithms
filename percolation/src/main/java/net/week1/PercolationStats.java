package net.week1;

import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;
/**
 * Created by karrie on 1/11/15.
 */
public class PercolationStats {
    private final double confidenceIntervalFactor = 1.96;
    private int numberExperiments;
    protected int[] vacancy;

    /**
     * perform T independent experiments on an N-by-N grid
     * @param N - size of square grid
     * @param T - number of experiments
     * @throws java.lang.IllegalArgumentException if either N ≤ 0 or T ≤ 0.
     */
    public PercolationStats(int N, int T){
        if(N <= 0) throw new IllegalArgumentException(Integer.toString(N));
        if(T <= 0) throw new IllegalArgumentException(Integer.toString(T));
        numberExperiments = T;
        vacancy = new int[numberExperiments];

        int expCnt = 0;
        //do experiment
        do{
            System.out.println("New experiment");
            Percolation p = new Percolation(N);
            int i;
            int j;
            int openCnt = 0;
            //open sites until percolate
            do{
                i = StdRandom.uniform(1, N +1);
                j = StdRandom.uniform(1, N +1);
                if(!p.isOpen(i, j)){
                    p.open(i, j);
                    openCnt++;
                }
            }while(!p.percolates());
            System.out.println("percolation!");
            vacancy[expCnt] = openCnt; //vacancy at time of percolation is estimate of threshold value
            expCnt++;
        }while(expCnt < numberExperiments);
        System.out.println("experiments complete");
    }

    /**
     * The sample mean of percolation threshold.
     * Provides an estimate of the percolation threshold.
     * Use standard statistics to compute the sample mean.
     * @return double
     */
    public double mean(){
        return StdStats.mean(vacancy);
    }

    /**
     * The sample standard deviation of percolation threshold.
     * Measures the sharpness of the threshold.
     * Use standard statistics to compute the sample standard deviation.
     * @return double
     */
    public double stddev(){
        if(numberExperiments == 1) return Double.NaN;
        return StdStats.stddev(vacancy);
    }

    /**
     * Low endpoint of 95% confidence interval for the percolation threshold
     * @return double
     */
    public double confidenceLo(){
        return mean() - (confidenceIntervalFactor * stddev() / Math.sqrt(numberExperiments));
    }

    /**
     * High endpoint of 95% confidence interval for the percolation threshold
     * @return double
     */
    public double confidenceHi(){
        return mean() + (confidenceIntervalFactor * stddev() / Math.sqrt(numberExperiments));
    }

    /**
     * test client
     * @param args size of square grid, number of experiments
     */
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() +"," + ps.confidenceHi());
    }
}
