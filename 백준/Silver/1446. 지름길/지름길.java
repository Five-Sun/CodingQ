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

        graph = new ArrayList[M + 1];
        for (int i = 0; i <= M; i++) {
            graph[i] = new ArrayList<>();
            graph[i].add(new Edge(i + 1, 1));
        }


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (u > M || v > M) continue;

            graph[u].add(new Edge(v, w));
        }
        int answer = dijkstra(0, M, new int[M + 1]);
        System.out.println(answer);
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
                if (next.v > fin) continue;
                if (dist[next.v] > cur.w + next.w) {
                    dist[next.v] = cur.w + next.w;
                    pq.offer(new Edge(next.v, cur.w + next.w));
                }
            }
        }
        return dist[fin];
    }
}
//지름길 정보를 저장,
//시작점부터 있는 위치에서 지름길이 있어? 있으면 이용해서 그 도착 지점 비용 갱신
