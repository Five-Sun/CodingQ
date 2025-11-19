import java.io.*;
import java.util.*;

class Main {
    static int answer = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(1);
            return;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }
        while (true) {
            q.poll();
            if (q.size() == 1) {
                System.out.println(q.poll());
                break;
            }
            int temp = q.poll();
            q.offer(temp);
        }
    }
}