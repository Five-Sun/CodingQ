import java.io.*;
import java.util.*;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Solution {
    static int T, N, K;
    static int[][] arr;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N + 1][2];
            dp = new int[N + 1][K + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int w = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                arr[i][0] = w;
                arr[i][1] = v;
            }

            for (int i = 1; i <= N; i++) {
                int w = arr[i][0];
                int v = arr[i][1];

                for (int j = 1; j <= K; j++) {
                    if (j - w < 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                    }
                }
            }

            int answer = 0;
            for (int i = 1; i <= K; i++) {
                answer = Math.max(answer, dp[N][i]);
            }

            sb.append("#" + tc + " " + answer + "\n");
        }
        System.out.println(sb.toString());
    }
}
/**
 * 0-1 가방 문제
 * dp[가방 개수][최대 부피] = 가치
 */
