import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] dist;
    static int[] parent, rank;
    static final int INF = 1_000_000;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int islandCnt = labelIslands();

        dist = new int[islandCnt + 1][islandCnt + 1];
        for (int i = 1; i <= islandCnt; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        findBridges(islandCnt);

        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= islandCnt; i++) {
            for (int j = i + 1; j <= islandCnt; j++) {
                if (dist[i][j] != INF) {
                    edges.add(new Edge(i, j, dist[i][j]));
                }
            }
        }

        Collections.sort(edges);
        makeSet(islandCnt);

        int mstCost = 0;
        int count = 0;

        for (Edge e : edges) {
            if (union(e.u, e.v)) {
                mstCost += e.w;
                count++;
                if (count == islandCnt - 1) break;
            }
        }

        if (count != islandCnt - 1) {
            System.out.println(-1);
        } else {
            System.out.println(mstCost);
        }
    }

    static int labelIslands() {
        int id = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1) {
                    id++;
                    bfs(r, c, id);
                }
            }
        }

        return id;
    }

    static void bfs(int sr, int sc, int id) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
        map[sr][sc] = id + 1; // 원래 1과 구분하기 위해 2부터 저장

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dx[d];
                int nc = c + dy[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (map[nr][nc] != 1) continue;

                map[nr][nc] = id + 1;
                q.offer(new int[]{nr, nc});
            }
        }
    }

    static void findBridges(int islandCnt) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] >= 2) {
                    for (int d = 0; d < 4; d++) {
                        scan(r, c, d);
                    }
                }
            }
        }
    }

    static void scan(int r, int c, int d) {
        int fromMapId = map[r][c];
        int from = fromMapId - 1;

        int nr = r + dx[d];
        int nc = c + dy[d];
        int len = 0;

        while (nr >= 0 && nr < N && nc >= 0 && nc < M) {
            if (map[nr][nc] == fromMapId) return;

            if (map[nr][nc] == 0) {
                len++;
                nr += dx[d];
                nc += dy[d];
                continue;
            }

            int to = map[nr][nc] - 1;
            if (len >= 2) {
                dist[from][to] = Math.min(dist[from][to], len);
                dist[to][from] = Math.min(dist[to][from], len);
            }
            return;
        }
    }

    static void makeSet(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }
        return true;
    }
}