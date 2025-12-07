import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Edge>[] graph;
    static int INF = 100_000_000;

    static class Edge {
        int v, w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }
        st = new StringTokenizer(br.readLine());
        int V1 = Integer.parseInt(st.nextToken());
        int V2 = Integer.parseInt(st.nextToken());

        int answer1 = dijkstra(1, V1, new int[N + 1]) + dijkstra(V1, V2, new int[N + 1]) + dijkstra(V2, N, new int[N + 1]);
        int answer2 = dijkstra(1, V2, new int[N + 1]) + dijkstra(V2, V1, new int[N + 1]) + dijkstra(V1, N, new int[N + 1]);
        int answer = Math.min(answer1, answer2);
        System.out.println(answer >= INF ?  -1 : answer);
    }

    static int dijkstra(int start, int fin, int[] dist) {

        Arrays.fill(dist, INF);
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (dist[cur.v] < cur.w) {
                continue;
            }

            for (Edge next : graph[cur.v]) {
                if (dist[next.v] > cur.w + next.w) {
                    dist[next.v] = cur.w + next.w;
                    pq.offer(new Edge(next.v, cur.w + next.w));
                }
            }
        }
        return dist[fin];
    }
}