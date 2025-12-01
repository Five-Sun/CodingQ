import java.io.*;
import java.util.*;

public class Main {
    static int[] dp;
    static int[][] wires;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        wires = new int[N + 1][2];
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            wires[i][0] = Integer.parseInt(st.nextToken());
            wires[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(wires, (a, b) -> {
            return a[0] - b[0];
        });

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (wires[j][1] < wires[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(N - max);
    }
}
