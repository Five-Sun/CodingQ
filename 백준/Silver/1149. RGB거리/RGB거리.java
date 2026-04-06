import java.io.*;
import java.util.*;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][]dp = new int[N][3];
        dp[0][0] += map[0][0];
        dp[0][1] += map[0][1];
        dp[0][2] += map[0][2];

        for (int i = 1; i < N; i++) {
            dp[i][0] += map[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] += map[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] += map[i][2] +Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        int answer = Math.min(Math.min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]);
        System.out.println(answer);
    }
}
/**
 * 사람 필요한 정보, 인덱스와 좌표 정보
 * 계단 필요한 정보, 인덱스와 좌표 정보, K
 * 스케줄 필요한 정보,
 */