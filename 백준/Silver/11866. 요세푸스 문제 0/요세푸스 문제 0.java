import java.io.*;
import java.util.*;

class Main {
    static int answer = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }
        StringBuilder sb = new StringBuilder("<");
        int count = 1;
        while (q.size() > 1) {
            if (count == K) {
                count = 1;
                sb.append(q.poll() + ", ");
            } else {
                count++;
                q.offer(q.poll());
            }
        }
        sb.append(q.poll() + ">");
        System.out.println(sb);
    }
}