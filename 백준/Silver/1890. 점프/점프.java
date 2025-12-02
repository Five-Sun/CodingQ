import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N][N];
        dp[0][0] = 1;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (r == N - 1 && c == N - 1) {
                    System.out.println(dp[r][c]);
                    return;
                }

                if (dp[r][c] != 0) {
                    int count = map[r][c];
                    if (c + count < N) {
                        dp[r][c + count] += dp[r][c];
                    }

                    if (r + count < N) {
                        dp[r + count][c] += dp[r][c];
                    }
                }
            }
        }
    }
}

