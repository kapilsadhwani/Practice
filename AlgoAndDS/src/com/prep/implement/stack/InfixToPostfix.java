package com.prep.implement.stack;
/* Java implementation to convert infix expression to postfix*/
// Note that here we use Stack class for Stack operations 
  
import java.util.Stack; 
  
class InfixToPostfix { 
    // A utility function to return precedence of a given operator 
    // Higher returned value means higher precedence 
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
       
    // The main method that converts given infix expression 
    // to postfix expression.  
    static String infixToPostfix(String expression) { 
    	char[] tokens = expression.toCharArray();
    	
        // initializing empty String for result 
        String result = new String(""); 
          
        // initializing empty stack 
        Stack<Character> ops = new Stack<>(); 

        for (int i = 0; i<tokens.length; ++i) { 
        	// Current token is a whitespace, skip it 
            if (tokens[i] == ' ') 
                continue;
              
             // If the scanned character is an operand, add it to output [Variable or Digit].
            if ((tokens[i] >= 'a' && tokens[i] <= 'z') || (tokens[i] >= '0' && tokens[i] <= '9'))
                result += tokens[i]; 
               
            // If the scanned character is an '(', push it to the stack. 
            else if (tokens[i] == '(') 
            	ops.push(tokens[i]); 
              
            //  If the scanned character is an ')', pop and output from the stack  
            // until an '(' is encountered. 
            else if (tokens[i] == ')') { 
                while (!ops.isEmpty() && ops.peek() != '(') 
                    result += ops.pop(); 
                  
                if (!ops.isEmpty() && ops.peek() != '(') 
                    return "Invalid Expression"; // invalid expression                 
                else
                	ops.pop(); 
            } 
            
            // an operator is encountered
            else { 
                while (!ops.isEmpty() && precedence(tokens[i]) <= precedence(ops.peek())) 
                    result += ops.pop(); 
                ops.push(tokens[i]); 
            } 
       
        } 
       
        // pop all the operators from the stack 
        while (!ops.isEmpty()) 
            result += ops.pop(); 
       
        return result; 
    } 
    
    // Driver method  
    public static void main(String[] args)  { 
        String exp = "a+b * (c^d-e) ^(f+g*h)- i "; 	//abcd^e-fgh*+^*+i-
        System.out.println(infixToPostfix(exp)); 
        
        exp = "a+b*(10 ^ d-258)^(f+ 13 *h)-i"; 	//abcd^e-fgh*+^*+i-
        System.out.println(infixToPostfix(exp));
    } 
} 