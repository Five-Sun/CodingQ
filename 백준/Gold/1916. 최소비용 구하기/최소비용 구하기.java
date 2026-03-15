import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Node>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int answer = djikstra(start, end);

        System.out.println(answer);
    }

    static int djikstra(int start, int end) {
        int[] dist = new int[N + 1];
        int INF = Integer.MAX_VALUE;
        for (int i = 0; i <= N; i++) {
            dist[i] = INF;
        }
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.v;
            int w = cur.w;

            if (dist[u] < w) continue;

            for (Node node : graph[u]) {
                int next = node.v;
                int nextW = node.w;
                int newDist = dist[u] + nextW;

                if (dist[next] > newDist) {
                    dist[next] = newDist;
                    pq.offer(new Node(next, newDist));
                }
            }
        }

        return dist[end];
    }

    static class Node implements Comparable<Node>{
        int v, w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
