package cs501.HW3;

import cs501.project.Node;

import javax.management.InvalidAttributeValueException;

import cs501.project.LinkedList;
import cs501.project.Stack;
import cs501.project.Queue;

// need ALL the comments
// at least two test cases (for generating postfix AND for evaluating)



public class HW3_main {

	
	
	
	public static void main(String[] args) {
		String expression = "A * B + (C - D / E)";
		String expected = "Queue [_data=['A', 'B', '*', 'C', 'D', 'E', '/', '-', '+']]";
		Queue<String> actual = ExpressionConverter.InfixToPostfix(expression);
		
		
		if (!expected.equals(actual.toString())) {
			System.out.println("not there yet");
		} else {
			System.out.println("huzzah!!");
		}
		
		
	}
	
}
