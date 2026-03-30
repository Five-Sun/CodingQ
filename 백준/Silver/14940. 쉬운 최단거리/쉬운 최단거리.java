import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        int sr = 0, sc = 0;
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                if(map[r][c] == 2) {
                    sr = r;
                    sc = c;
                }
            }
        }

        int[][] answer = dijkstra(sr, sc);
        for(int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 0) {
                    sb.append("0 ");
                } else if(answer[r][c] == INF) {
                    sb.append("-1 ");
                } else {
                    sb.append(answer[r][c] + " ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int[][] dijkstra(int sr, int sc) {
        int[][] dist = new int[N][M];
        for (int[] d : dist) {
            Arrays.fill(d, INF);
        }
        dist[sr][sc] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{sr, sc, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int d = cur[2];

            if(dist[r][c] < d) continue;

            for(int i=0; i<4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                int nd = d + 1;
                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if(map[nr][nc] == 0) continue;
                if(dist[nr][nc] > nd) {
                    dist[nr][nc] = nd;
                    pq.offer(new int[]{nr, nc, nd});
                }
            }
        }

        return dist;
    }
}
