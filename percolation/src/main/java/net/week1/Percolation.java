package net.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by karrie on 1/11/15.
 */
public class Percolation {
    final int BLOCKED = 0;  // blocked, closed site
    final int OPEN = 1;  // open site
    final int FULL = 2;  // full site

    private int sizeOfSquareGrid;
    private int numberOfObjects;
    protected WeightedQuickUnionUF wquf;
    int sites[][];

    /**
     * Create N-by-N grid, with all sites blocked.
     * should take time proportional to N2.
     * @param N
     */
    public Percolation(int N)  {
        if(N <= 0) throw new IllegalArgumentException(Integer.toString(N));
        sizeOfSquareGrid = N;
        numberOfObjects = N * N;

        wquf = new WeightedQuickUnionUF(numberOfObjects + 2);
        sites = new int[sizeOfSquareGrid][sizeOfSquareGrid];
        for (int i=0; i < N; i++)
            for (int j=0; j < N; j++)
                sites[i][j] = BLOCKED;

        connectToVirtualSite(sites.length + 1, 1);
        connectToVirtualSite(sites.length + 2, N);
    }

    private void connectToVirtualSite(int virtualSite, int row){
        for(int i =1; i <= sizeOfSquareGrid; i++){
            wquf.union(xyTo1D(sizeOfSquareGrid, virtualSite), xyTo1D(row, i));
        }
    }

    /**
     *  Map from a 2-dimensional (row, column) pair to a 1-dimensional union find object index
     */
    private int xyTo1D(int i, int j){
        return sizeOfSquareGrid * (i-1) + j -1;
    }

    /**
     * i and j are integers between 1 and N,
     * @param i
     * @param j
     */
    private void validateIndices(int i, int j){
        if(i < 1 || i > sizeOfSquareGrid) throw new IndexOutOfBoundsException(Integer.toString(i));
        if(j < 1 || j > sizeOfSquareGrid) throw new IndexOutOfBoundsException(Integer.toString(j));
    }

    /**
     * open site (row i, column j) if it is not open already
     * union() to all adjacent open sites (up to 4 calls to union())
     * @param i
     * @param j
     */
    public void open(int i, int j)  {
        validateIndices(i, j);
        sites[i -1][j -1] = OPEN;
        int p = xyTo1D(i,j);

        //edge cases.....see what i did there?
        if(i > 1 && isOpen(i-1, j)) wquf.union(p, xyTo1D(i-1,j));
        if( i < sizeOfSquareGrid && isOpen(i+1, j)) wquf.union(p, xyTo1D(i+1,j));
        if(j > 1 && isOpen(i, j-1)) wquf.union(p, xyTo1D(i,j-1));
        if(j < sizeOfSquareGrid && isOpen(i, j+1)) wquf.union(p, xyTo1D(i,j+1));
    }

    /**
     * is site (row i, column j) open?
     * @param i
     * @param j
     * @return
     */
    public boolean isOpen(int i, int j){
        validateIndices(i, j);
        return sites[i-1][j-1] == OPEN || sites[i-1][j-1] == FULL ;
    }

    /**
     * full site is an open site that can be connected to an open site in the top row
     * // is site (row i, column j) full?
     * full if root is less than or equal to N
     * find()
     * connected()
     * @param i
     * @param j
     * @return
     */
    public boolean isFull(int i, int j){
        validateIndices(i, j);

        boolean isOpen = isOpen(i, j);
        if(!isOpen) return false;
        boolean isFull;

        if(!percolates()){
            isFull = isOpen && wquf.connected(xyTo1D(i, j), numberOfObjects);
        }
        else{
            //since bottom row is pre-connected to virtual site, need different approach to prevent 'backwash' bug after percolation
            //above site
            boolean isAboveFull =  i > 1 && sites[i -2][j -1] == FULL;
            //left site
            boolean isLeftFull = j > 1 && sites[i - 1][j -2] == FULL;
            //right site
            boolean isRightFull = j < sizeOfSquareGrid && sites[i -1][j] == FULL;
            //below site
            boolean isBelowFull = i < sizeOfSquareGrid && sites[i][j -1] == FULL;
            isFull  = isOpen && (isAboveFull || isRightFull || isBelowFull || isLeftFull || i == 1);
        }

        if(isFull){
            sites[i -1][j -1] = FULL;
        }
        return isFull;
    }

    /**
     *  Does the system percolate?
     *  The system percolates if there is a full site in the bottom row.
     *  Evaluate if top virtual site is connected to bottom virtual site.
     * @return
     */
    public boolean percolates(){
        return wquf.connected(numberOfObjects, numberOfObjects + 1);
    }

    /**
     * Directly test xyTo1D() & open() using the main() function of Percolation.
     * @param args
     */
    public static void main(String[] args){
        Percolation percolation = new Percolation(5);

        System.out.println("should be 0 : "+ percolation.xyTo1D(1, 1));
        System.out.println("should be 20 : "+ percolation.xyTo1D(5, 1));
        System.out.println("should be 24 : "+ percolation.xyTo1D(5, 5));

        //test open()
        percolation.open(5, 2);
        percolation.open(5, 3);
        System.out.println(percolation.wquf.connected(percolation.xyTo1D(5, 2), percolation.xyTo1D(5, 3)));

        percolation.open(1, 1);
        percolation.open(1, 2);
        System.out.println(percolation.wquf.connected(percolation.xyTo1D(1, 1), percolation.xyTo1D(1, 2)));

        System.out.println("check virtual site setup");
        System.out.println(percolation.wquf.find(1));
        System.out.println(percolation.wquf.find(2));
        System.out.println(percolation.wquf.find(0));
        System.out.println(percolation.wquf.find(4));
        System.out.println(percolation.wquf.find(6));
        System.out.println(percolation.wquf.find(7));
        System.out.println(percolation.wquf.find(8));
        System.out.println(percolation.wquf.find(percolation.numberOfObjects));
        System.out.println(percolation.wquf.find(percolation.numberOfObjects + 1));
    }
}
