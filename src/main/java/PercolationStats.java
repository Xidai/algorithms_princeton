public class PercolationStats {
    private double[] probabilities;
    private int t;

    public PercolationStats(int N, int T) {
        t = T;
        checkBoundary(N, T);
        probabilities = new double[T];
        for (int i = 0; i < T; i++) {
            double count = 0;
            Percolation percolation = new Percolation(N);
            while (!percolation.percolates()) {
                int x = StdRandom.uniform(1, N + 1);
                int y = StdRandom.uniform(1, N + 1);
                if (!percolation.isOpen(x, y)) {
                    percolation.open(x, y);
                    count++;
                }
            }
            probabilities[i] = count / (N * N);
        }
    }    // perform T independent computational experiments on an N-by-N grid

    private void checkBoundary(int n, int t) {
        if (n <= 0 || t <= 0) throw new IllegalArgumentException("Illegal Argument!");
    }

    public double mean() {
        return StdStats.mean(probabilities);
    }                    // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(probabilities);
    }                  // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(t);
    }            // returns lower bound of the 95% confidence interval

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    }           // returns upper bound of the 95% confidence interval

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);

        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }  // test client, described below

}
