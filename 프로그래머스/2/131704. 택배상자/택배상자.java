import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int idx = 0;
        Stack<Integer> stack = new Stack<>();

        for (int box = 1; box <= order.length; box++) {
            if (box == order[idx]) {
                answer++;
                idx++;

                while (!stack.isEmpty() && stack.peek() == order[idx]) {
                    stack.pop();
                    answer++;
                    idx++;

                    if (idx == order.length) {
                        return answer;
                    }
                }
            } else {
                stack.push(box);
            }
        }

        while (!stack.isEmpty() && idx < order.length) {
            if (stack.peek() == order[idx]) {
                stack.pop();
                answer++;
                idx++;
            } else {
                break;
            }
        }

        return answer;
    }
}