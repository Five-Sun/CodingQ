import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][N + 1]; // 요소, 일수

        int[][] table = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            table[i][0] = T;
            table[i][1] = P;
        }

        //초기값
        dp[0][0] = 0;

        for (int i = 1; i <= N; i++) {
            int T = table[i][0];
            int P = table[i][1];

            for (int j = i; j <= N; j++) {
                if (i + T - 1 <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][i -1] + P);
                } else {
                    dp[i][j] = dp[i -1][j];
                }
            }
        }
        System.out.println(dp[N][N]);
    }
}