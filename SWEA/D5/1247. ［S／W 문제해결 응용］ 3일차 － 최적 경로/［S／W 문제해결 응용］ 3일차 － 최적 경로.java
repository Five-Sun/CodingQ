import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] customer;
    static boolean[] visited;
    static int homeX, homeY, companyX, companyY;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            customer = new int[N][2];
            visited = new boolean[N];
            answer = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            companyX = Integer.parseInt(st.nextToken());
            companyY = Integer.parseInt(st.nextToken());
            homeX = Integer.parseInt(st.nextToken());
            homeY = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                customer[i][0] = Integer.parseInt(st.nextToken());
                customer[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(0, companyX, companyY, 0);

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int depth, int curX, int curY, int sum) {
        if (sum >= answer) return;

        if (depth == N) {
            sum += distance(curX, curY, homeX, homeY);
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            dfs(depth + 1, customer[i][0], customer[i][1],
                sum + distance(curX, curY, customer[i][0], customer[i][1]));
            visited[i] = false;
        }
    }

    static int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}