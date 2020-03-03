
package Traversals;

import java.util.ArrayList;
import java.util.Stack;

public class verificationResult {


    public static boolean checkNumbers(String expression, int[] numbers){

        String[] string = expression.trim().split("[+|\\-|*|/|(|)|]");

        for(int i=0;i<string.length;i++){
            string[i] = string[i].trim();
        }
        String[] string1 = new String[4];
        int n = 0;

        for(int i = 0;i<string.length;i++){
            if(string[i]!=null&&string[i]!=" "&&string[i].length()!=0&&!string[i].equals("")){
                string1[n] = string[i];
                n++;
               System.out.println(string[i]);
            }
        }


        int equal = 0;
        for(int i = 0;i< numbers.length;i++){
            for(int j = 0;j<string1.length;j++){

               if(string1[i].equals(String.valueOf(numbers[j]))){
                    equal++;
                }
            }
        }
        System.out.println(equal);
        if(equal>=4){
            return true;
        }
        else{
            return false;
        }

    }

    /** Evaluate an expression */
    public static double evaluateExpression(String expression) {
        // Create operandStack to store operands
        Stack<Double> operandStack = new Stack<Double>();

        // Create operatorStack to store operators
        Stack<Character> operatorStack = new Stack<Character>();

        // Extract operands and operators
        java.util.StringTokenizer tokens = new java.util.StringTokenizer(
                expression, "()+-/*", true);

        // Phase 1: Scan tokens
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim(); // Extract a token
            if (token.length() == 0) // Blank space
                continue; // Back to the while loop to extract the next
                // token
            else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
                // Process all +, -, *, / in the top of the operator stack
                while (!operatorStack.isEmpty()
                        && (operatorStack.peek().equals('+')
                        || operatorStack.peek().equals('-')
                        || operatorStack.peek().equals('*') || operatorStack
                        .peek().equals('/'))) {
                    processAnOperator(operandStack, operatorStack);
                }

                // Push the + or - operator into the operator stack
                operatorStack.push(new Character(token.charAt(0)));
            } else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
                // Process all *, / in the top of the operator stack
                while (!operatorStack.isEmpty()
                        && (operatorStack.peek().equals('*') || operatorStack
                        .peek().equals('/'))) {
                    processAnOperator(operandStack, operatorStack);
                }

                // Push the * or / operator into the operator stack
                operatorStack.push(new Character(token.charAt(0)));
            } else if (token.trim().charAt(0) == '(') {
                operatorStack.push(new Character('(')); // Push '(' to stack
            } else if (token.trim().charAt(0) == ')') {
                // Process all the operators in the stack until seeing '('
                while (!operatorStack.peek().equals('(')) {
                    processAnOperator(operandStack, operatorStack);
                }

                operatorStack.pop(); // Pop the '(' symbol from the stack
            } else { // An operand scanned
                // Push an operand to the stack
                operandStack.push(new Double(token));
            }
        }

        // Phase 2: process all the remaining operators in the stack
        while (!operatorStack.isEmpty()) {
            processAnOperator(operandStack, operatorStack);
        }

        // Return the result
        return ((Double) (operandStack.pop())).doubleValue();
    }

    public static void processAnOperator(Stack<Double> operandStack, Stack<Character> operatorStack) {
        if (operatorStack.peek().equals('+')) {
            operatorStack.pop();
            double op1 = ((Double) (operandStack.pop())).doubleValue();
            double op2 = ((Double) (operandStack.pop())).doubleValue();
            operandStack.push(new Double(op2 + op1));
        } else if (operatorStack.peek().equals('-')) {
            operatorStack.pop();
            double op1 = ((Double) (operandStack.pop())).doubleValue();
            double op2 = ((Double) (operandStack.pop())).doubleValue();
            operandStack.push(new Double(op2 - op1));
        } else if (operatorStack.peek().equals('*')) {
            operatorStack.pop();
            double op1 = ((Double) (operandStack.pop())).doubleValue();
            double op2 = ((Double) (operandStack.pop())).doubleValue();
            operandStack.push(new Double(op2 * op1));
        } else if (operatorStack.peek().equals('/')) {
            operatorStack.pop();
            double op1 = ((Double) (operandStack.pop())).doubleValue();
            double op2 = ((Double) (operandStack.pop())).doubleValue();
            operandStack.push(new Double(op2 / op1));
        }
    }
}
