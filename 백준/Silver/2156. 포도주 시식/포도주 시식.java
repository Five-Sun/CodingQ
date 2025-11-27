import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] wine = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(wine[1]);
            return;
        }
        if (N == 2) {
            System.out.println(wine[1] + wine[2]);
            return;
        }

        int[] dp = new int[N + 1];

        dp[1] = wine[1];
        dp[2] = wine[1] + wine[2];

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
        }
        System.out.println(dp[N]);
    }
}
//case 1: i번째꺼 안마시면: dp[i-1]
//case 2: i번째꺼 먹고, 전꺼 안먹음: dp[i] = dp[i-2] + wine[i]
//case 3: i번째꺼 먹고, 전꺼 먹음: dp[i] = dp[i-3] + wine[i-1] + wine[i]