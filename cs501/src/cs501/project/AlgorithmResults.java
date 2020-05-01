package cs501.project;

//used to track the performance of the sorting algorithm and store the sorted list
public class AlgorithmResults {
    int comparisons = 0;
    int exchanges = 0;
    int assignments = 0;
    int loops = 0;
    
    @Override
    public String toString() {
        return "comparisons: " + comparisons + ", exchanges: " + exchanges + ", assignments: " + assignments + ", loops: " + loops;
    }
}