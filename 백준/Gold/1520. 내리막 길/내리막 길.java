import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    static int[][] map;
    static int N, M;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[] row : dp) Arrays.fill(row, -1);
        System.out.println(dfs(0, 0));
    }

    static int dfs(int r, int c) {
        if (r == N - 1 && c == M - 1) {
            return 1;
        }
        if (dp[r][c] != -1) return dp[r][c];

        dp[r][c] = 0;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
            if (map[nr][nc] >= map[r][c]) continue; // 내리막만 가능

            dp[r][c] += dfs(nr, nc);
        }

        return dp[r][c];
    }
}