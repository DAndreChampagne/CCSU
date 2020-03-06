package cs501.HW3;

import cs501.project.Queue;
import cs501.project.Stack;

public class ExpressionConverter {

	private static boolean isOperand(String s) {
		return !IsOperator(s);
	}
	
	private static boolean IsOperator(String s) {
		switch (s) {
			case "+":
			case "-":
			case "*":
			case "/":
			case "(":
			case ")":
				return true;
			default:
				return false;
		}
	}
	
	private static int OperandPriority(String s) {
		// PEMDAS, but () are last
		switch (s) {
			case "^":
				return 3;
			case "*":
			case "/":
				return 2;
			case "+":
			case "-":
				return 1;
			default:
				return -1;
		}
	}
	
	
	// https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
	public static Queue<String> InfixToPostfix(String expression) {
		Queue<String> infix = new Queue<>(String.class);
		Stack<String> operators = new Stack<>(String.class);
		Queue<String> postfix = new Queue<>(String.class);
		
		// populate infix queue from given expression
		for (String s : expression.replace(" ",  "").split("")) {
			infix.Enqueue(s);
		}
		
		
		while (!infix.Empty()) {			
			String token = infix.Dequeue();
			
			if (isOperand(token) && token != "(" && token != ")") {
				postfix.Enqueue(token);
			} else if (token.equals("(")) {
				operators.Push(token);
			} else if (token.equals(")")) {
				while (!operators.Empty() && !operators.Top().equals("(")) {
					String op = operators.Pop();
					postfix.Enqueue(op);
				}
				operators.Pop();
			} else {
				while (!operators.Empty() && OperandPriority(token) <= OperandPriority(operators.Top())) {
					String op = operators.Pop();
					postfix.Enqueue(op);
				}
				operators.Push(token);
			}
		}
		
		while (!operators.Empty()) {
			String op =  operators.Pop();
			postfix.Enqueue(op);
		}
		
		return postfix;
	}
	
}
