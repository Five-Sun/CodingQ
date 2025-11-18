import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Stack<Character> stack = new Stack<>();
        char prev = ' ';
        int answer = 0;
        for (char ch : str.toCharArray()) {
            if(stack.isEmpty()) {
                stack.push(ch);
                prev = ch;
                continue;
            }

            if (prev == '(' && ch == '(') {
                stack.push(ch);
            } else if (prev == '(' && ch == ')') {
                stack.pop();
                answer += stack.size();
                prev = ch;
            } else if (prev == ')' && ch == ')') {
                stack.pop();
                answer += 1;
            } else {
                stack.push(ch);
                prev = ch;
            }
        }
        System.out.println(answer);
    }
}
//1. 레이저를 쏘면 stack에 남아있는 갯수만큼 조각이 생김
//2. 막대기가 끝나는 구간은 1개가 생긴다. (이건 직전의 문자가 ')') 인 경우야
//3. 전의 문자가 '('  -> '(' 면 push, '(' -> ')' 면 레이저 pop + size(), ')' -> ')' 면 + 1, ')' -> '('는 푸시!
//EX) 3 + 3 + 1 + 3 + 1 + 2 + 1 + 1 + 1 + 1