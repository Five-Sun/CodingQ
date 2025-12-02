import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][M + 1];
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[N + 1][M + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                dp[r][c] = Math.max(dp[r - 1][c], Math.max(dp[r][c - 1], dp[r - 1][c - 1])) + map[r][c];
            }
        }
        System.out.println(dp[N][M]);
    }
}
//위, 대각선 위, 옆에서 올 수 있음