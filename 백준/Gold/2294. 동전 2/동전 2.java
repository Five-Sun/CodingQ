import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Integer[] coins = new Integer[N];
        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
        }
        int[] dp = new int[M + 1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            int coin = coins[i];
            for (int j = 1; j <= M; j++) {
                if (j - coin >= 0) {
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }

        if (dp[M] == 10001) {
            System.out.println(-1);
        } else {
            System.out.println(dp[M]);
        }
    }
}
