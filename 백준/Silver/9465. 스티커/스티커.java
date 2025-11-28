import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][N + 1];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (N == 1) {
                System.out.println(Math.max(arr[0][1], arr[1][1]));
                continue;
            }

            int[][] dp = new int[2][N + 1];
            dp[0][0] = 0;
            dp[1][0] = 0;

            for (int i = 1; i <= N; i++) {
                dp[0][i] = Math.max(dp[1][i - 1] + arr[0][i], dp[0][i -1]);
                dp[1][i] = Math.max(dp[0][i - 1] + arr[1][i], dp[1][i -1]);
            }

            System.out.println(Math.max(dp[0][N], dp[1][N]));
        }
    }
}
//0 0 선택하면 1 0, 0 1 선택 불가
//1 0  -> 0 0, 1, 1 선택 불가
// 0 50 40 200 140 250
// 0 30 100 120 210 260
//[i][0] -> [i - 1][1] , [i -2][0], [i -2][1] 비교
//[i][1] -> [i - 1][0] , [i -2][0], [i -2][1] 비교
//0 50 50 200 200 250
//0 30 100 120 210 260

