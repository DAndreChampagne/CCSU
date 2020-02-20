package cs501;

//interface for sorting algorithm
public interface ISortingAlgorithm {
    public String Name();
    public SortingAlgorithmResults Sort(Integer[] data);
    public SortingAlgorithmResults SortInPlace(Integer[] data);
}
