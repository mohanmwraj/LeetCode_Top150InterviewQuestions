package stack;

import java.util.Stack;

/*
    https://leetcode.com/problems/min-stack/description/
 */
public class minStack {
    /*
        * Approach: Stack of Min Value Pairs
        *
     */
    class MinStack_1 {
        private Stack<int[]> stack = new Stack<>();

        public MinStack_1() {}

        public void push(int x) {
            /* If the stack is empty, then the min value
             * must just be the first value we add. */
            if (stack.isEmpty()) {
                stack.push(new int[] { x, x });
                return;
            }

            int currentMin = stack.peek()[1];
            stack.push(new int[] { x, Math.min(x, currentMin) });
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(n)
     */

    /*
        * Approach: Two Stacks
        *
        * The main Stack should keep track of the order numbers arrived (a standard Stack)
        * the second Stack should keep track of the current minimum.
     */
    class MinStack_2 {
        private Stack<Integer> stack = new Stack<>();
        private Stack<Integer> minStack = new Stack<>();

        public MinStack_2() {}

        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty() || x <= minStack.peek()) {
                minStack.push(x);
            }
        }

        public void pop() {
            if (stack.peek().equals(minStack.peek())) {
                minStack.pop();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(n)
     */

    /*
        * Approach: Improved Two Stacks
        *
        * One downside of above solution is that if the same number is pushed repeatedly onto MinStack,
        * and that number also happens to be the current minimum, there'll be a lot of needless repetition on the min-tracker Stack.
        *
        * An improvement is to put pairs onto the min-tracker Stack.
     */
    class MinStack_3 {
        private Stack<Integer> stack = new Stack<>();
        private Stack<int[]> minStack = new Stack<>();

        public MinStack_3() {}

        public void push(int x) {
            // We always put the number onto the main stack.
            stack.push(x);

            // If the min stack is empty, or this number is smaller than
            // the top of the min stack, put it on with a count of 1.
            if (minStack.isEmpty() || x < minStack.peek()[0]) {
                minStack.push(new int[] { x, 1 });
            }
            // Else if this number is equal to what's currently at the top
            // of the min stack, then increment the count at the top by 1.
            else if (x == minStack.peek()[0]) {
                minStack.peek()[1]++;
            }
        }

        public void pop() {
            // If the top of min stack is the same as the top of stack
            // then we need to decrement the count at the top by 1.
            if (stack.peek().equals(minStack.peek()[0])) {
                minStack.peek()[1]--;
            }

            // If the count at the top of min stack is now 0, then remove
            // that value as we're done with it.
            if (minStack.peek()[1] == 0) {
                minStack.pop();
            }

            // And like before, pop the top of the main stack.
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek()[0];
        }
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(n)
     */
}
