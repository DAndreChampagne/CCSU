package cs501.HW3;

import cs501.HW3.LinkedList;



public class HW3_main {
	
	public static void TowerOfHanoi(int num, int from, int temp, int to) {
		System.out.println(String.format("TowerOfHanoi(%d, %d, %d, %d)", num, from, temp, to));
		
		if (num == 1)
		   System.out.println ("\tDisk 1 moved from " + from + " to " + to);
		else {
		   TowerOfHanoi (num-1, from, to, temp);
		   System.out.println ("\tDisk " + num + " moved from " + from + " to " + to);  
		   TowerOfHanoi (num-1, temp, from, to);       
		}
	}
	
	
	public static void main(String[] args) {
//		ILinkedList<Integer> list = new LinkedList<>();

		TowerOfHanoi(3, 1, 2, 3);
		
	}
	
}
