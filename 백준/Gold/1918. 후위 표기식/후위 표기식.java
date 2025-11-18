import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch  >= 'A' && ch <= 'Z') { //피연산자의 경우
                sb.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            } else if (ch == '*' || ch == '/') {
                while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                    sb.append(stack.pop());
                }
                stack.push(ch);
            } else if (ch == '+' || ch == '-') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.push(ch);
            }

        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }
}
//스택의 특징 선입후출
//1. 우선순위 (,) 3 -> *, / 2 -> +, - 1
//2. 우선순위가 높거나 같은(숫자가 크거나 같은) 연산자가 stack에 있으면 pop 해야함
//3. ABC 문자열이 끝나면 남은 stack도 한번 털어줘야해
//4. 괄호라는 특수한 친구를 어떻게 처리할 것인가? 괄호 다음에 오는 숫자는 더 우선순위가 높아지는 효과가 있는데
//5. 닫는 괄호가 나오면 여는 괄호까지 다 pop()
