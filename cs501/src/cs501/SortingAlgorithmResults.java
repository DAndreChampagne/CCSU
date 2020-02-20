package cs501;

//used to track the performance of the sorting algorithm and store the sorted list
public class SortingAlgorithmResults {
    int[] results;
    int comparisons = 0;
    int exchanges = 0;

    @Override
    public String toString() {
        return "n: " + results.length + ", comparisons: " + comparisons + ", exchanges: " + exchanges;
    }
}