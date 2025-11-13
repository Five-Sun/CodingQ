import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static long [][] costs;
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        costs = new long[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(costs[i], INF);
            costs[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            costs[a][b] = Math.min(costs[a][b], c);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (costs[i][k] != INF && costs[k][j] != INF) {
                        costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(costs[i][j] == INF ? 0 : costs[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}