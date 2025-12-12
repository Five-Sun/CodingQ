import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<String> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            q.offer(str);
        }
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            if (!q.peek().equals(str)) {
                q.remove(str);
                answer++;
            } else {
                q.poll();
            }
        }
        System.out.println(answer);
    }
}