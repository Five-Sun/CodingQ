import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> list = new ArrayList<>();
        int[] degree = new int[N +1];

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            list.get(first).add(next);
            degree[next]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i < N + 1; i++) {
            if (degree[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur + " ");
            for (int next : list.get(cur)) {
                degree[next]--;
                if (degree[next] == 0) {
                    pq.offer(next);
                }
            }
        }
        System.out.println(sb);
    }
}