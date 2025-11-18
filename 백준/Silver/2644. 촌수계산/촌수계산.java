import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        int answer = bfs(A, B, new boolean[n + 1], graph);
        System.out.println(answer);
    }

    static int bfs(int u, int v, boolean[] visited, List<Integer>[] graph) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{u, 0});
        visited[u] = true;
        int result = -1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int cost = cur[1];

            if (node == v) {
                result = cost;
                break;
            }

            for (int next : graph[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new int[]{next, cost + 1});
                }
            }
        }
        return result;
    }
}