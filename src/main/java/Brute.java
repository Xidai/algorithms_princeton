public class Brute {
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point points[] = new Point[N];
        for (int i = 0; i < N; i++){
            int x = in.readInt();
            int y = in.readInt();
            Point point = new Point(x, y);
            points[i] = point;
        }
        searchForLineSegment(points);
    }

    private static void searchForLineSegment(Point[] points) {
        int count = points.length;
        for (int i = 0; i < count - 3; i++){
            for (int j = i + 1; j < count - 2; j++){
                for (int k = j + 1; k < count - 1; k++){
                    for (int o = k + 1; o < count; o++){
                        Point p1 = points[i];
                        Point p2 = points[j];
                        Point p3 = points[k];
                        Point p4 = points[o];
                        checkIfIsLineSegment(p1, p2, p3, p4);
                    }
                }
            }
        }
    }

    private static void checkIfIsLineSegment(Point p1, Point p2, Point p3, Point p4) {
    }
}
