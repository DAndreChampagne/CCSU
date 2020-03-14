package cs501.HW3;

import cs501.project.Queue;
import cs501.project.Stack;
import cs501.project.Tuple;

public class ExpressionConverter {

	private static boolean IsOperand(String s) {
		return !IsOperator(s);
	}
	
	private static boolean IsNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	private static boolean IsOperator(String s) {
		switch (s) {
			case "^":
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
	
	private static Double getValue(String paramater, Tuple<String, Double>[] tuples) {
		for (Tuple<String, Double> t : tuples) {
			if (t.first.equals(paramater)) {
				return t.second;
			}
		}
		
		throw new IllegalArgumentException("value not found in array");
	}
	
	private static Double evaluate(String operation, Double x, Double y) {
		switch (operation) {
			case "^": return Math.pow(x, y);
			case "+": return x+y;
			case "-": return x-y;
			case "*": return x*y;
			case "/": return x/y;
			default:
				throw new IllegalArgumentException(operation + " is not supported");
		}
	}
	
	
	// https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
	public static Queue<String> InfixToPostfix(String expression) {
		Queue<String> infix = new Queue<>(String.class);
		Stack<String> operators = new Stack<>(String.class);
		Queue<String> postfix = new Queue<>(String.class);
		
		String temp = "";
		
		// populate infix queue from given expression
		for (String s : expression.replace(" ",  "").split("")) {
			if (IsNumeric(s))
				temp += s;
			else {
				if (temp.length() > 0)
					infix.Enqueue(temp);
				infix.Enqueue(s);
				temp = "";
			}
		}
		
		if (temp.length() > 0)
			infix.Enqueue(temp);
		
		while (!infix.Empty()) {			
			String token = infix.Dequeue();
			
			if (IsOperand(token) && token != "(" && token != ")") {
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
	
	@SafeVarargs
	public static Double EvaluatePostfixExpression(String expression, Tuple<String, Double> ... parameters) {
		Queue<String> q = new Queue<>(String.class);
		Stack<Double> values = new Stack<>(Double.class);
		
		// populate postfix queue from given expression
//		for (String s : expression.replace(" ",  "").split("")) {
//			q.Enqueue(s);
//		}
		String temp = "";
		
		// populate infix queue from given expression
		for (String s : expression.replace(" ",  "").split("")) {
			if (IsNumeric(s))
				temp += s;
			else {
				if (temp.length() > 0)
					q.Enqueue(temp);
				q.Enqueue(s);
				temp = "";
			}
		}
		
		if (temp.length() > 0)
			q.Enqueue(temp);
		
		
		while (!q.Empty()) {
			String token = q.Dequeue();
			
			if (IsOperand(token)) {
				values.Push(IsNumeric(token) ? Double.parseDouble(token) : getValue(token, parameters));
			} else {
				Double x,y;
				
				y = values.Pop();
				x = values.Pop();
				
				values.Push(evaluate(token, x, y));
			}
		}
		
		Double result = values.Pop();
		
		return result;
	}
	
}
