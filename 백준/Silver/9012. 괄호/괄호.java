import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            String str = br.readLine();
            boolean flag = true;
            Stack<Character> stack = new Stack<>();

            for (char ch : str.toCharArray()) {
                if (ch == '(' || ch == ')') {
                    if (ch == ')') {
                        if (!stack.isEmpty() && stack.peek() == '(') {
                            stack.pop();
                        } else {
                            flag = false;
                            break;
                        }
                    } else {
                        stack.push(ch);
                    }
                }

            }
            if (flag && stack.isEmpty()) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }
}