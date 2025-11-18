import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int nextPush  = 1;
        
        boolean flag = false;
        for (int i = 0; i < T; i++) {
            int target = Integer.parseInt(br.readLine());

            while (nextPush <= target) {
                stack.push(nextPush++);
                sb.append("+\n");
            }

            if (stack.peek() == target) {
                stack.pop();
                sb.append("-\n");
            } else {
                System.out.println("NO");
                return;
            }
                
        }
        System.out.println(sb);
    }
}