//package cs501;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
//import cs501.HW1.BubbleSort;
//import cs501.HW1.InsertionSort;
//import cs501.HW1.SelectionSort;
//
////@SuppressWarnings("unchecked")
//public class HW1 {
//
//	public static void main(String[] args) throws FileNotFoundException {
//		
//		int nums = 2000;
//        int min = 1;
//        int max = nums;
//        String path;
//
//        Integer[] best = new Integer[nums];
//        Integer[] worst = new Integer[nums];
//        Integer[] data = new Integer[nums];
//        
//        ISortingAlgorithm[] algorithms = new ISortingAlgorithm[] {
//			new BubbleSort(),
//			new SelectionSort(),			
//			new InsertionSort(),
//	    };
//		SortingAlgorithmResults results;
//        
//		
//		System.out.println("\n\n*****************************************************");
//		System.out.println("Prototype versions, n = " + 10 + ":");
//		System.out.println("*****************************************************\n");
//		
//		best = new Integer[] {0,1,2,3,4,5,6,7,8,9};
//        worst = new Integer[] {9,8,7,6,5,4,3,2,1,0};
//        data = new Integer[] { 0,9,3,2,1,6,5,8,7,4 };
//		
//		// run each algorithm
//		for (ISortingAlgorithm algorithm : algorithms) {
//			System.out.println(algorithm.Name());
//			
//			results = algorithm.Sort(best);
//			System.out.print("Best case: ");
//			System.out.println(results.toString());
//			System.out.println("\tbefore: " + Helpers.PrintArray(best));
//			System.out.println("\tafter: " + Helpers.PrintArray(results.results));
//			  		
//			results = algorithm.Sort(worst);
//			System.out.print("Worst case: ");
//			System.out.println(results.toString());
//			System.out.println("\tbefore: " + Helpers.PrintArray(worst));
//			System.out.println("\tafter: " + Helpers.PrintArray(results.results));
//			  		
//			results = algorithm.Sort(data);
//			System.out.print("Average case: ");
//			System.out.println(results.toString());
//			System.out.println("\tbefore: " + Helpers.PrintArray(data));
//			System.out.println("\tafter: " + Helpers.PrintArray(results.results));
//			  		
//			System.out.println();
//		}
//		
//
//		System.out.println("\n\n*****************************************************");
//		System.out.println("Final versions, n = " + nums + ":");
//		System.out.println("*****************************************************\n");
//		
//		
//		best = new Integer[nums];
//        worst = new Integer[nums];
//        data = new Integer[nums];
//        
//		
//        // initialize arrays
//		best[0] = min;
//		worst[0] = max;
//		for (int i=1; i<nums; ++i) {
//		    best[i] = best[i-1] + 1;
//		    worst[i] = worst[i-1] - 1;
//		}
//
//		
//		// if the random number file does not exist, create it
//		path = "data/hw1_data.txt";
//		if (!Files.exists(Paths.get(path))) {
//		    try {
//		        Helpers.GenerateRandomNumberFile(path, nums, min, max);
//		    } catch (Exception ex) {
//		        System.out.println("Cannot generate number file");
//		        return;
//		    }
//		}
//
//		
//		// load numbers from file
//		try {
//		    data = Helpers.ImportNumbersFromFile(path);
//		} catch (Exception ex) {
//		    System.out.println("Cannot find number file");
//		    return;
//		}
//		
//		
//		
//		// run each algorithm
//		for (ISortingAlgorithm algorithm : algorithms) {
//			System.out.println(algorithm.Name());
//			
//			results = algorithm.Sort(best);
//			System.out.print("Best case: ");
//			System.out.println(results.toString());
//			  		
//			results = algorithm.Sort(worst);
//			System.out.print("Worst case: ");
//			System.out.println(results.toString());
//			  		
//			results = algorithm.Sort(data);
//			System.out.print("Average case: ");
//			System.out.println(results.toString());
//			  		
//			System.out.println();
//		}
//		
//		
//		
//		System.out.println("\n\n*****************************************************");
//		System.out.println("Problem #2");
//		System.out.println("*****************************************************\n");
//		
//		System.out.println("Fibbonacci(6) = " + HW1.Fibbonacci(6));
//		System.out.println("Factorial(5) = " + HW1.Factorial(5));
//		System.out.println("BinarySearch(new int[] {0,1,2,3,4,5,6,7,8,9}, 0, 9, 2) = " + HW1.BinarySearch(new int[] {0,1,2,3,4,5,6,7,8,9},0,9,2));
//		
//	}
//	
//	
//	
//	public static int Fibbonacci(int n) {
//		return n < 2 ? n : Fibbonacci(n - 1) + Fibbonacci(n - 2);
//	}
//	
//	public static int Factorial(int n){
//		return (n == 0) ? 1 : n * Factorial(n-1);
//	 }   
//	
//	public static int BinarySearch(int x[], int low, int high, int y) { 
//        if (high >= low) { 
//            int mid = low + (high - low) / 2; 
//  
//            if (x[mid] == y) 
//                return mid; 
//  
//            // check the left side
//            if (x[mid] > y) 
//                return BinarySearch(x, low, mid - 1, y); 
//  
//            // check the right side
//            return BinarySearch(x, mid + 1, high, y); 
//        } 
//  
//        // not found
//        return -1; 
//    }
//	
//	
//    
//    public static class BubbleSort implements ISortingAlgorithm {
//        public String Name() { return "Bubble Sort"; }
//
//        public SortingAlgorithmResults Sort(Integer[] data) { return SortInPlace(data.clone()); }
//        public SortingAlgorithmResults SortInPlace(Integer[] data) {
//            SortingAlgorithmResults results = new SortingAlgorithmResults();
//            
//            Boolean swapMade = true;
//            for (int i = 0; i < data.length-1 && swapMade; ++i) {
//                swapMade = false;
//                results.loops++;
//                for (int j=0; j < (data.length - i - 1); ++j) {
//                    results.loops++;
//                    results.comparisons++;
//                    if (data[j] > data[j+1]) {
//                        results.exchanges++;
//                        swapMade = true;
//                        Integer temp = data[j];
//                        data[j] = data[j+1];
//                        data[j+1] = temp;
//                    }
//                }
//            }
//            
//            results.results = data;
//            return results;
//        }
//    }
//
//    public static class SelectionSort implements ISortingAlgorithm {
//        public String Name() { return "Selection Sort"; }
//
//        public SortingAlgorithmResults Sort(Integer[] data) { return SortInPlace(data.clone()); }
//        public SortingAlgorithmResults SortInPlace(Integer[] data) {
//            SortingAlgorithmResults results = new SortingAlgorithmResults();
//
//            for (int i=0; i < data.length-1; ++i) {
//                results.loops++;
//                int min=i;
//
//                for (int j=i+1; j<data.length; ++j) {
//                    results.loops++;
//                    results.comparisons++;
//                    if (data[j] < data[min]) {
//                        min = j;
//                    }
//                }
//
//                if (min == i)
//                    break;
//
//                Integer temp = data[min];
//                data[min] = data[i];
//                data[i] = temp;
//                results.exchanges++;
//
//            }
//            
//            results.results = data;
//            return results;
//        }
//    }
//
//    public static class InsertionSort implements ISortingAlgorithm {
//        public String Name() { return "Insertion Sort"; }
//
//        public SortingAlgorithmResults Sort(Integer[] data) { return SortInPlace(data.clone()); }
//        public SortingAlgorithmResults SortInPlace(Integer[] data) {
//            SortingAlgorithmResults results = new SortingAlgorithmResults();
//
//            for (int i=1; i < data.length; ++i) {
//                results.loops++;
//                Integer current = data[i];
//                int j = i-1;
//
//                results.comparisons++;
//                while (j >= 0 && data[j] > current) {
//                    results.loops++;
//                    results.exchanges++;
//                    data[j+1] = data[j];
//                    --j;
//                }
//                results.exchanges++;
//                data[j+1] = current;
//            }
//            
//            results.results = data;
//            return results;
//        }
//    }
//
//
//
//
//    
//    
////    public static void TowerOfHanoi(int num, int from, int temp, int to) {
////      if (num == 1)
////         System.out.println ("Disk 1 moved from " + from + " to " + to);
////      else {
////         TowerOfHanoi (num-1, from, to, temp);
////         System.out.println ("Disk " + num + " moved from " + from + " to " + to);  
////         TowerOfHanoi (num-1, temp, from, to);       
////      }
////   }
//
//} 