package cs501.HW3;

import cs501.project.Queue;
import cs501.project.Tuple;

public class HW3_main {
	
	// shortcut for adding parameters to the evaluation function
	public static Tuple<String, Double> T(String param, Double value) {
		return new Tuple<String, Double>(param, value);
	}
	
	public static void main(String[] args) {
		String expression = null;
		String expected = null;
		Queue<String> actual = null;
		Double result = null;
		
		expression = "A * B + (C - D / E)";
		expected = "AB*CDE/-+";
		actual = ExpressionConverter.InfixToPostfix(expression);
		result = ExpressionConverter.EvaluatePostfixExpression(actual.toRawString(),
				T("A", 5.0),
				T("B", 3.0),
				T("C", 6.0),
				T("D", 8.0),
				T("E", 2.0)
		);
		System.out.println("'" + expression + "' = '" + actual.toRawString() + "' = " + result);
				
		
		expression = "A * B + C";
		expected = "AB*C+";
		actual = ExpressionConverter.InfixToPostfix(expression);
		result = ExpressionConverter.EvaluatePostfixExpression(actual.toRawString(),
				T("A", 10.0),
				T("B", 2.0),
				T("C", 2.0)
		);
		System.out.println("'" + expression + "' = '" + actual.toRawString() + "' = " + result);
	
		
		expression = "X^2";
		expected = "X2^";
		actual = ExpressionConverter.InfixToPostfix(expression);
		result = ExpressionConverter.EvaluatePostfixExpression(actual.toRawString(),
				T("X", 10.0)
		);
		System.out.println("'" + expression + "' = '" + actual.toRawString() + "' = " + result);
		
		
		expression = "(X ^ 2) + 10";
		expected = "X 2 ^ 10 +";
		actual = ExpressionConverter.InfixToPostfix(expression);
		result = ExpressionConverter.EvaluatePostfixExpression(actual.toRawString(),
				T("X", 10.0)
		);
		System.out.println("'" + expression + "' = '" + actual.toRawString() + "' = " + result);
		
	}	
}
