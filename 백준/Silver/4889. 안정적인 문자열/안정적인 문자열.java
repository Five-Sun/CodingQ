import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str;
        int T = 0;
        while (true) {
            T++;
            str = br.readLine();
            if (str.charAt(0) == '-') {
                break;
            }

            Stack<Character> stack = new Stack<>();

            for (char ch : str.toCharArray()) {
                if (stack.isEmpty()) {
                    stack.push(ch);
                } else {
                    if (ch == '}' && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        stack.push(ch);
                    }
                }
            }

            int count = 0;
            boolean ready = false;
            while (!stack.isEmpty()) {
                if (!ready && stack.peek() == '}') {
                    ready = true;
                } else if (!ready && stack.peek() == '{') {
                    count++;
                    ready = true;
                } else if (ready && stack.peek() == '}') {
                    count++;
                    ready = false;
                } else {
                    ready = false;
                }
                stack.pop();
            }

            sb.append(T).append(". ").append(count).append("\n");
        }
        System.out.println(sb);
    }
}