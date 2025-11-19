import java.io.*;
import java.util.*;

class Main {
    static int[] priority;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st;
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            //왼쪼부터 0번째
            Queue<Integer> q = new ArrayDeque<>();
            priority = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                q.offer(i);
                priority[i] = Integer.parseInt(st.nextToken());
            }

            int count = 0;
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (isPossible(cur)) {
                    count++;
                    priority[cur] = -1;
                    if (cur == M) {
                        sb.append(count).append("\n");
                        break;
                    }
                } else {
                    q.offer(cur);
                }
            }
        }
        System.out.println(sb);
    }
    //출력할 수 있고,
    static boolean isPossible(int num) {
        for (int i = 0; i < priority.length; i++) {
            if (priority[num] < priority[i]) {
                return false;
            }
        }
        return true;
    }
}