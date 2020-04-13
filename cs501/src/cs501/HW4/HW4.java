package cs501.HW4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class HW4 {

	// allows for easier insertion of algorithms in the future
	public static interface ISortingAlgorithm {
		public String Name();
		public SortingAlgorithmResults Sort(int[] data);
	}
	
	
	//used to track the performance of the sorting algorithm and store the sorted list
	public static class SortingAlgorithmResults {
	    int[] sortedData;
	    int comparisons = 0;
	    int exchanges = 0;

	    @Override
	    public String toString() {
	        return "n: " + sortedData.length + ", comparisons: " + comparisons + ", exchanges: " + exchanges;
	    }
	}

	
	// returns a string containing a printable version of an array
	public static String PrintArray(int[] data) {
        String result = "[ ";

        for (int item : data) {
            result += item + ", ";
        }
        
        result += "]";

        return result;
    }
	
	
	// helper function to ensure that the list is sorted
	public static boolean IsSortedAscending(int[] data) {
		for (int i=1; i<data.length; ++i) {
			if (data[i-1] > data[i])
				return false;
		}
		return true;
	}
	
	
	// generates a random number file and saves it
	public static void GenerateRandomNumberFile(String path, int count, double min, double max) throws FileNotFoundException {
        String data = "";
        Random r = new Random();

        for (; count > 0; --count) {
            int x = (int)(min + (r.nextDouble() * (max-min)));
            data += x + " ";
        }

        PrintWriter writer = new PrintWriter(path);
        writer.print(data);
        writer.close();
    }

	
    // loads random number file into an array
    public static int[] ImportNumbersFromFile(String path) throws IOException {
        String data = ""; 
        data = String.join("", Files.readAllLines(Paths.get(path)));
        
        String[] intermediate = data.split(" ", 0);
        int[] results = new int[intermediate.length];
        
        for (int i=0; i<intermediate.length; ++i) {
            Integer value = (int)Double.parseDouble(intermediate[i]);
            results[i] = value;
        }
        
        return results;
    }
	
    
    public static class HeapSort implements ISortingAlgorithm {
    	public String Name() { return "Heap Sort"; }
    	
    	private int[] upheap(int[] data, int j) {
    		int i = j;
    		int key = data[i];
    		int k = i/2;
    		
    		while (k >= 1 && data[k] < key) {
    			data[i] = data[k];
    			i = k;
    			k = k/2;
    		}
    		
    		data[i] = key;
    		
    		return data;
    	}
    	
    	public SortingAlgorithmResults Sort(int[] data) {
    		int[] x = data.clone();
    		SortingAlgorithmResults results = new SortingAlgorithmResults();
    		
    		
    		
    		return results;
    	}
    }
   

    
    
	
  
	public static void main(String[] args) throws Exception {

		int nums = 10;
		ISortingAlgorithm[] algorithms = {
			new HeapSort(),
		};
		
        int[] best;
        int[] worst;
        int[] average;
		SortingAlgorithmResults results;
        String path;
        
        
		System.out.println("*****************************************************");
		System.out.println("Prototype versions, n = " + nums + ":");
		System.out.println("*****************************************************\n");
		
		best = new int[] {0,1,2,3,4,5,6,7,8,9};
        worst = new int[] {9,8,7,6,5,4,3,2,1,0};
        average = new int[] { 0,9,3,2,1,6,5,8,7,4 };
        
        // run the various algorithms
        for (ISortingAlgorithm algorithm : algorithms) {
        	System.out.println(algorithm.Name());
            
    		results = algorithm.Sort(best);
    		System.out.print("Best case: ");
    		System.out.println(results.toString());
    		System.out.println("\tbefore: " + PrintArray(best));
    		System.out.println("\tafter: " + PrintArray(results.sortedData));
    		  		
    		results = algorithm.Sort(worst);
    		System.out.print("Worst case: ");
    		System.out.println(results.toString());
    		System.out.println("\tbefore: " + PrintArray(worst));
    		System.out.println("\tafter: " + PrintArray(results.sortedData));
    		  		
    		results = algorithm.Sort(average);
    		System.out.print("Average case: ");
    		System.out.println(results.toString());
    		System.out.println("\tbefore: " + PrintArray(average));
    		System.out.println("\tafter: " + PrintArray(results.sortedData));
    		  		
    		System.out.println();
        }

		
		// ************************************************************************************************
		
		
//		nums = 2000;
//		int min = 0;
//		int max = nums;
//				
//		System.out.println("\n\n*****************************************************");
//		System.out.println("Final versions, n = " + nums + ":");
//		System.out.println("*****************************************************\n");
//		
//		best = new int[nums];
//        worst = new int[nums];
//        average = new int[nums];
//        
//        // initialize arrays for best and worst case
//		best[0] = min;
//		worst[0] = max;
//		for (int i=1; i<nums; ++i) {
//		    best[i] = best[i-1] + 1;	// 1, 2, ... n
//		    worst[i] = worst[i-1] - 1;	// n, n-1, ..., 2, 1
//		}
//		
//		// if the random number file does not exist, create it
//		path = "data/hw1_data.txt";
//		if (!Files.exists(Paths.get(path))) {
//		    try {
//		        GenerateRandomNumberFile(path, nums, min, max);
//		    } catch (Exception ex) {
//		        System.out.println("Cannot generate number file");
//		        return;
//		    }
//		}
//
//		// load numbers from file for average case
//		try {
//		    average = ImportNumbersFromFile(path);
//		} catch (Exception ex) {
//		    System.out.println("Cannot find number file");
//		    return;
//		}
//		
//		// run the various algorithms
//		for (ISortingAlgorithm algorithm : algorithms) {
//        	System.out.println(algorithm.Name());
//            
//    		results = algorithm.Sort(best);
//    		System.out.print("Best case: ");
//    		System.out.println(results.toString());
////    		System.out.println("\tbefore: " + PrintArray(best));
////    		System.out.println("\tafter: " + PrintArray(results.sortedData));
//    		  		
//    		results = algorithm.Sort(worst);
//    		System.out.print("Worst case: ");
//    		System.out.println(results.toString());
////    		System.out.println("\tbefore: " + PrintArray(worst));
////    		System.out.println("\tafter: " + PrintArray(results.sortedData));
//    		  		
//    		results = algorithm.Sort(average);
//    		System.out.print("Average case: ");
//    		System.out.println(results.toString());
////    		System.out.println("\tbefore: " + PrintArray(average));
////    		System.out.println("\tafter: " + PrintArray(results.sortedData));
//    		  		
//    		System.out.println();
//        }
		
		
		
	}
	
}
