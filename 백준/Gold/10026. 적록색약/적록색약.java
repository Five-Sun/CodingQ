import java.io.*;
import java.util.*;

class Main {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        //제공되는 맵
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < N; c++) {
                map[r][c] = str.charAt(c);
            }
        }
        //일반 사람부터 먼저 bfs탐색
        int A = bfs(N);

        //적록색약 맵
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 'G') {
                    map[r][c] = 'R';
                }
            }
        }
        int B = bfs(N);

        System.out.println(A + " " + B);
    }

    static int bfs(int N) {
        int count = 0;
        boolean[][] visited = new boolean[N][N];

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!visited[r][c]) {
                    count++;

                    Queue<int[]> q = new ArrayDeque<>();
                    q.offer(new int[]{r, c});
                    visited[r][c] = true;

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int curR = cur[0];
                        int curC = cur[1];
                        char curCh = map[r][c];

                        for (int i = 0; i < 4; i++) {
                            int nextR = curR + dr[i];
                            int nextC = curC + dc[i];

                            if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N) continue;
                            if (map[nextR][nextC] != curCh) continue;
                            if (visited[nextR][nextC]) continue;

                            visited[nextR][nextC] = true;
                            q.offer(new int[]{nextR, nextC});
                        }
                    }
                }
            }
        }
        return count;
    }
}