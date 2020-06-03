package com.implement.stack;
/* A Java program to evaluate a given expression where tokens are separated  
   by space. 
   Test Cases: 
     "10 + 2 * 6"            ---> 22 
     "100 * 2 + 12"          ---> 212 
     "100 * ( 2 + 12 )"      ---> 1400 
     "100 * ( 2 + 12 ) / 14" ---> 100     
*/ 
import java.util.Stack; 
  
public class EvaluateExpression { 
    public static int evaluate(String expression) { 
        char[] tokens = expression.toCharArray(); 
  
         // Stack for numbers: 'values' 
        Stack<Integer> values = new Stack<Integer>(); 
  
        // Stack for Operators: 'ops' 
        Stack<Character> ops = new Stack<Character>(); 
  
        for (int i = 0; i < tokens.length; i++) { 
             // Current token is a whitespace, skip it 
            if (tokens[i] == ' ') 
                continue; 
  
            // Current token is a number, push it to stack for numbers 
            if (tokens[i] >= '0' && tokens[i] <= '9') { 
                StringBuffer sbuf = new StringBuffer(); 
                // There may be more than one digits in number 
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') 
                    sbuf.append(tokens[i++]); 
                values.push(Integer.parseInt(sbuf.toString())); 
            } 
  
            // Current token is an opening brace, push it to 'ops' 
            else if (tokens[i] == '(') 
                ops.push(tokens[i]); 
  
            // Closing brace encountered, solve entire brace 
            else if (tokens[i] == ')') { 
                while (!ops.isEmpty() && ops.peek() != '(') 
                  values.push(applyOp(ops.pop(), values.pop(), values.pop())); 
                ops.pop(); 
            } 
  
            // Current token is an operator. 
            else if (tokens[i] == '+' || tokens[i] == '-' || 
                     tokens[i] == '*' || tokens[i] == '/') 
            { 
                // While top of 'ops' has same or greater precedence to current 
                // token, which is an operator. Apply operator on top of 'ops' 
                // to top two elements in values stack 
                //while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
            	while (!ops.empty() && precedence(tokens[i]) <= precedence(ops.peek()))
                  values.push(applyOp(ops.pop(), values.pop(), values.pop())); 
  
                // Push current token to 'ops'. 
                ops.push(tokens[i]); 
            } 
        } 
  
        // Entire expression has been parsed at this point, apply remaining 
        // ops to remaining values 
        while (!ops.empty()) 
            values.push(applyOp(ops.pop(), values.pop(), values.pop())); 
  
        // Top of 'values' contains result, return it 
        return values.pop(); 
    } 
  
    // Returns true if 'op2' has higher or same precedence as 'op1', 
    // otherwise returns false. 
    public static boolean hasPrecedence(char thisOp, char opsStackOp) { 
        if (opsStackOp == '(' || opsStackOp == ')') 
            return false; 
        if ((thisOp == '^') && (opsStackOp == '*' || opsStackOp == '/' || opsStackOp == '+' || opsStackOp == '-')) 
            return false; 
        if ((thisOp == '*' || thisOp == '/') && (opsStackOp == '+' || opsStackOp == '-')) 
            return false; 
        return true; 
    } 
    
    static int precedence(char ch) { 
        switch (ch) { 
	        case '+': 
	        case '-': 
	            return 1; 
	       
	        case '*': 
	        case '/': 
	            return 2; 
	       
	        case '^': 
	            return 3; 
        } 
        return -1; 
    }
  
    // A utility method to apply an operator 'op' on operands 'a'  
    // and 'b'. Return the result. 
	public static int applyOp(char op, int b, int a) {
		switch (op) {
			case '+':
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				if (b == 0)
					throw new UnsupportedOperationException("Cannot divide by zero");
				return a / b;
			case '^':
				return a ^ b;
		}
		return 0;
	}
  
    // Driver method to test above methods 
    public static void main(String[] args) { 
        System.out.println("10 + 2 * 6 = " + EvaluateExpression.evaluate("10 + 2 * 6")); 
        System.out.println("100 * 2 + 12 = " + EvaluateExpression.evaluate("100 * 2 + 12")); 
        System.out.println("100 * ( 2 + 12 ) = " + EvaluateExpression.evaluate("100 * ( 2 + 12 )")); 
        System.out.println("100 * ( 2 + 12 ) / 14 = " + EvaluateExpression.evaluate("100 * ( 2 + 12 ) / 14"));
    } 
}