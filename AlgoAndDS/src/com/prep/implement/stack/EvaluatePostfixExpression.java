package com.prep.implement.stack;
// Java proram to evaluate value of a postfix expression 
  
import java.util.Stack; 
  
public class EvaluatePostfixExpression  { 
    // Method to evaluate value of a postfix expression 
    static int evaluatePostfix(String expression) { 
    	char[] tokens = expression.toCharArray();
    	
        //create a stack 
        Stack<Integer> values=new Stack<>(); 
          
        // Scan all characters one by one 
        for(int i=0;i<tokens.length;i++) { 
        	// Current token is a whitespace, skip it 
            if (tokens[i] == ' ') 
                continue;
              
            // If the scanned character is an operand (number here), 
            // push it to the stack. 
            if (tokens[i] >= '0' && tokens[i] <= '9') { 
                StringBuffer sbuf = new StringBuffer(); 
                // There may be more than one digits in number 
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') 
                    sbuf.append(tokens[i++]); 
                values.push(Integer.parseInt(sbuf.toString())); 
            } 
              
            //  If the scanned character is an operator, pop two 
            // elements from stack apply the operator 
			else {
				int b = values.pop();
				int a = values.pop();

				switch (tokens[i]) {
					case '+':
						values.push(a + b);
						break;
	
					case '-':
						values.push(a - b);
						break;
	
					case '/':
						if (b == 0)
							throw new UnsupportedOperationException(
									"Cannot divide by zero");
						values.push(a / b);
						break;
	
					case '*':
						values.push(a * b);
						break;
				}
			}
        } 
        return values.pop();     
    } 
      
    // Driver program to test above functions 
    public static void main(String[] args)  { 
        String exp="2 3 1 *+ 9 -"; 
        System.out.println("postfix evaluation: "+evaluatePostfix(exp)); 
    } 
} 