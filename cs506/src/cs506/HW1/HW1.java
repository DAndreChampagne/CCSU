package cs506.HW1;

public class HW1 {
	public static int findLast(int[] x, int y) {
        for (int i=x.length-1; i>=0; --i) {
            if (x[i] == y)
                return i;
        }
        return -1;
    }

    public static int lastZero(int[] x) {
        for (int i=x.length-1; i>=0; --i) {
            if (x[i] == 0)
                return i;
        }
        return -1;
    }

    public static int countPositive(int[] x) {
        int count = 0;
        for (int i=0; i<x.length; ++i) {
            if (x[i] > 0)
                ++count;
        }
        return count;
    }

    public static int oddOrPos(int[] x) {
        int count=0;
        for (int i=0; i<x.length; ++i) {
            if (Math.abs(x[i]%2) == 1 || x[i] > 0)
                ++count;
        }
        return count;
    }
}
