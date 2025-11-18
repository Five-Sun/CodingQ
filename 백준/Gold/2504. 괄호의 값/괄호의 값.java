import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        int total = 0;
        int temp = 1;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '(') {
                temp *= 2;
                stack.push(ch);
            } else if (ch == '[') {
                temp *= 3;
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.peek() == '[') {
                    System.out.println(0);
                    return;
                } else {
                    if (str.charAt(i - 1) == '(') {
                        total += temp;
                    }
                    stack.pop();
                    temp /=2;
                }
            } else if (ch == ']') {
                if (stack.isEmpty() || stack.peek() == '(') {
                    System.out.println(0);
                    return;
                } else {
                    if (str.charAt(i - 1) == '[') {
                        total += temp;
                    }
                    stack.pop();
                    temp /=3;
                }
            }
        }
        if (!stack.isEmpty()) {
            System.out.println(0);
            return;
        }
        System.out.println(total);
    }
}