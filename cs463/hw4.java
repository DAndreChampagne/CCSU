import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

class hw4 {

    public static class Point {
        final double x;
        final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        // calculate the geometric distance between the two points
        public double distance(Point that) {
            /* Compute the distance between this and that */
            double x = that.x - this.x;
            double y = that.y - this.y;

            return Math.sqrt(x*x + y*y);
        }
    }

    public static class Pair {
        Point one;
        Point two;
        double distance; // added this because I want to see the shortest distance in the driver program

        public Pair(Point one, Point two, double distance) {
            this.one = one; this.two = two; this.distance = distance;
        }
    }

    public static class ClosestPair {
        private static Pair findClosestPair(List<Point> points) {
            double closest = Double.MAX_VALUE;
            Pair result = null;

            // brute force method
            // compare each point and calculate the distance between them. If the current distance is less than 'closest',
            // replace the Pair with the new pair of points
            for (int i=0; i<points.size(); ++i) {
                for (int j=i+1; j<points.size(); ++j) {
                    double distance = points.get(i).distance(points.get(j));
                    if (distance < closest) {
                        closest = distance;
                        result = new Pair(points.get(i), points.get(j), distance); // this pair is closer than the last, update result
                    }
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        List<Point> points = new ArrayList<>();

        // comment out these lines and uncomment below to have 50 points randomly generated
        int N = scanner.nextInt();
        for (int i = 0; i < N; i+=1) {
            points.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
        }
        scanner.close();

        // debug code to generate the values, so I don't have to keep typing them
        // Random r = new Random();
        // for (int i=0; i<50; ++i) {
        //     double x = r.nextDouble()*50.0;
        //     double y = r.nextDouble()*50.0;
        //     System.out.println("Creating point (" + x + ", " + y + ")");

        //     points.add(new Point(x,y));
        // }
        
        Pair closest = ClosestPair.findClosestPair(points);

        System.out.println("\nClosest pair (distance of " + closest.distance + "):");
        System.out.println(closest.one.x + " " + closest.one.y);
        System.out.println(closest.two.x + " " + closest.two.y);
    }
}