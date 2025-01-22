package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

/*
    https://leetcode.com/problems/evaluate-reverse-polish-notation/description/

    Input: tokens = ["2","1","+","3","*"]
    Output: 9
    Explanation: ((2 + 1) * 3) = 9
 */
public class evaluateReversePolishNotation {
    /*
        * Approach: Reducing the List In-place
        *
        * A pointer is used to step through the list, and each time an operator (`*+-/) is found,
        * that operator is then applied to the 2 values before it (which are always numbers,
        * as long as the original input was valid).
        * The 3 values are then replaced with the result.
        * This process is repeated until the list is of length 1, containing a single number that is the answer to be returned.
        *
     */
    private static final Map<
                String,
                BiFunction<Integer, Integer, Integer>
                > OPERATIONS = new HashMap<>();

    // Ensure this only gets done once for ALL test cases.
    static {
        OPERATIONS.put("+", (a, b) -> a + b);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("*", (a, b) -> a * b);
        OPERATIONS.put("/", (a, b) -> a / b);
    }

    public int evalRPN_1(String[] tokens) {
        int currentPosition = 0;
        int length = tokens.length; // We need to keep track of this ourselves.

        while (length > 1) {
            // Move the position pointer to the next operator token.
            while (!OPERATIONS.containsKey(tokens[currentPosition])) {
                currentPosition++;
            }

            // Extract the operation and numbers to apply operation too.
            String operation = tokens[currentPosition];
            int number1 = Integer.parseInt(tokens[currentPosition - 2]);
            int number2 = Integer.parseInt(tokens[currentPosition - 1]);

            // Calculate the result to overwrite the operator with.
            BiFunction<Integer, Integer, Integer> operator = OPERATIONS.get(
                    operation
            );
            int value = operator.apply(number1, number2);
            tokens[currentPosition] = Integer.toString(value);

            // Delete numbers and point pointers correctly.
            delete2AtIndex(tokens, currentPosition - 2, length);
            currentPosition--;
            length -= 2;
        }

        return Integer.parseInt(tokens[0]);
    }

    private void delete2AtIndex(String[] tokens, int d, int length) {
        for (int i = d; i < length - 2; i++) {
            tokens[i] = tokens[i + 2];
        }
    }
    /*
        Without Lambda
     */
    public int evalRPN_2(String[] tokens) {
        int currentPosition = 0;
        int length = tokens.length; // We will need to keep track of this ourselves.

        while (length > 1) {
            // Move the position pointer to the next operator token.
            while (!"+-*/".contains(tokens[currentPosition])) {
                currentPosition++;
            }

            // Extract the numbers.
            int number1 = Integer.parseInt(tokens[currentPosition - 2]);
            int number2 = Integer.parseInt(tokens[currentPosition - 1]);

            // Calculate the result to overwrite the operator with.
            int newValue = 0;
            switch (tokens[currentPosition]) {
                case "+":
                    newValue = number1 + number2;
                    break;
                case "-":
                    newValue = number1 - number2;
                    break;
                case "*":
                    newValue = number1 * number2;
                    break;
                case "/":
                    newValue = number1 / number2;
                    break;
            }
            tokens[currentPosition] = Integer.toString(newValue);

            // Delete numbers and point pointers correctly.
            delete2AtIndex(tokens, currentPosition - 2, length);
            currentPosition--;
            length -= 2;
        }

        return Integer.parseInt(tokens[0]);
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */

    /*
        * Approach: Evaluate With Stack
        *
        * Visit each operator, in linear order. Finding these can be done with a linear search of the original list.
        * Get the 2 most recently seen numbers that haven't yet been replaced. These could be tracked using a Stack.
        *
                stack = new Stack()
                for each token in tokens:
                    if token is a number:
                        stack.push(token)
                    else (token is operator):
                        number_2 = stack.pop()
                        number_1 = stack.pop()
                        result = apply_operator(token, number_1, number_2)
                        stack.push(result)
                return stack.pop()
         *
     */
    private static final Map<
            String,
            BiFunction<Integer, Integer, Integer>
            > OPERATIONS_1 = new HashMap<>();

    // Ensure this only gets done once for ALL test cases.
    static {
        OPERATIONS_1.put("+", (a, b) -> a + b);
        OPERATIONS_1.put("-", (a, b) -> a - b);
        OPERATIONS_1.put("*", (a, b) -> a * b);
        OPERATIONS_1.put("/", (a, b) -> a / b);
    }

    public int evalRPN_3(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (!OPERATIONS_1.containsKey(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            int number2 = stack.pop();
            int number1 = stack.pop();
            BiFunction<Integer, Integer, Integer> operation;
            operation = OPERATIONS_1.get(token);
            int result = operation.apply(number1, number2);
            stack.push(result);
        }

        return stack.pop();
    }

    /*
        Without Lambda
     */
    public int evalRPN_4(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (!"+-*/".contains(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            int number2 = stack.pop();
            int number1 = stack.pop();

            int result = 0;

            switch (token) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    result = number1 / number2;
                    break;
            }

            stack.push(result);
        }

        return stack.pop();
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

}
