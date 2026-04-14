import java.io.*;
import java.util.*;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    static int N;
    static int[][] map;
    static int answer;
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            dfs(0, i, i, new boolean[N], 0);
        }

        System.out.println(answer);
    }

    static void dfs(int depth, int start, int idx, boolean[] visited, int dist) {
        if (depth == N && start == idx) {
            answer = Math.min(answer, dist);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (i != idx && !visited[i] && map[idx][i] != 0) {
                visited[i] = true;
                dfs(depth + 1, start, i, visited, dist + map[idx][i]);
                visited[i] = false;
            }
        }
    }
}
