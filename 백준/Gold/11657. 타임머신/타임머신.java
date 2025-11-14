import java.io.*;
import java.util.*;

class Main {
    static long[] dist;
    static boolean[] cycle;
    static long INF = 1_000_000_000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dist = new long[N + 1];
        cycle = new boolean[N + 1];
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges.add(new Edge(A, B, C));
        }

        if (bellmanFord(N, edges, 1)) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i] == INF) dist[i] = -1;
                System.out.println(dist[i]);
            }
        }
    }

    static boolean bellmanFord(int n, List<Edge> edges, int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int i = 1; i <= n - 1; i++) {
            boolean upt = false;
            for (Edge e : edges) {
                if (dist[e.u] != INF && dist[e.v] > dist[e.u] + e.w) {
                    dist[e.v] = dist[e.u] + e.w;
                    upt = true;
                }
            }
            if (!upt) break; //갱신될 것이 없는데 계속 돌리면 낭비
        }

        for (Edge e : edges) {
            if (dist[e.u] != INF && dist[e.v] > dist[e.u] + e.w) {
                return true;
            }
        }

        return false;
    }
    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}