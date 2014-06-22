public class Percolation {
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF bottomUF;
    private int N;
    private int virtualNode;
    private boolean[] openSites;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N is out of boundary!");
        virtualNode = N * N;
        uf = new WeightedQuickUnionUF(N * N + 1); //uf.id[N * N] is the virtual top node.
        uf = new WeightedQuickUnionUF(N);
        this.N = N;
        openSites = new boolean[N * N];
        for (int i = 0; i < N * N; i++) {
            openSites[i] = false;
        }
    }              // create N-by-N grid, with all sites blocked

    public void open(int i, int j) {
        checkBound(i, j);
        int site = toOneDimension(i, j);
        openSites[site] = true;
        if (isTopNode(i, j)) {
            uf.union(site, virtualNode);
        }
        if (!isOutOfBoundary(i, j - 1) && isOpen(i, j - 1)) { //left
            uf.union(site, toOneDimension(i, j - 1));
        }
        if (!isOutOfBoundary(i - 1, j) && isOpen(i - 1, j)) { //up
            uf.union(site, toOneDimension(i - 1, j));
        }
        if (!isOutOfBoundary(i, j + 1) && isOpen(i, j + 1)) { //right
            uf.union(site, toOneDimension(i, j + 1));
        }
        if (!isOutOfBoundary(i + 1, j) && isOpen(i + 1, j)) { //down
            uf.union(site, toOneDimension(i + 1, j));
        }
    }         // open site (row i, column j) if it is not already

    public boolean isOpen(int i, int j) {
        checkBound(i, j);
        return openSites[toOneDimension(i, j)];
    }    // is site (row i, column j) open?

    public boolean isFull(int i, int j) {
        checkBound(i, j);
        return uf.connected(toOneDimension(i, j), virtualNode);
    }   // is site (row i, column j) full?

    public boolean percolates() {
//        for (int i = N * N - 1; i > N * N - N - 1; i--) {
//            if (uf.connected(i, virtualNode)) {
//                return true;
//            }
//        }
//        return false;
        while (true){

        }
    }

    private boolean isTopNode(int i, int j) {
        return i == 1 && (j > 0 && j <= N);
    }

    private int toOneDimension(int i, int j) {
        return (i - 1) * N + (j - 1);
    }

    private boolean isOutOfBoundary(int i, int j) {
        return (i <= 0 || i > N || j <= 0 || j > N);
    }

    private void checkBound(int i, int j) {
        if (i <= 0 || i > N || j <= 0 || j > N) throw new IndexOutOfBoundsException("row index out of bounds");
    }
}