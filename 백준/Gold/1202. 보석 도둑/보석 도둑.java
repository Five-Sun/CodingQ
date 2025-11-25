import java.io.*;
import java.util.*;

import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> jewels = new PriorityQueue<>((a ,b) -> {
            return a[0] - b[0];
        });
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels.offer(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            return b[1] - a[1];
        });
        long answer = 0;
        while (!pq.isEmpty()) {
            int bag = pq.poll();
            while (!jewels.isEmpty() && jewels.peek()[0] <= bag) {
                maxHeap.add(jewels.poll());
            }
            if (!maxHeap.isEmpty()) {
                answer += maxHeap.poll()[1];
            }
        }
        System.out.println(answer);
    }
}
//보석도 작은 거 부터
//가방은 작은 거부터