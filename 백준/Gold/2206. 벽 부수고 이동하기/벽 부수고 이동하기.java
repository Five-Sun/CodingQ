import java.io.*;
import java.util.*;

class Main {
    static int[][] map;
    static int answer;
    static int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; // 지도
        ArrayList<int[]> walls = new ArrayList<>(); //벽 위치 기억할 리스트

        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        }
        answer = INF;
        bfs(N, M);

        System.out.println(answer == INF ? -1 : answer);
    }
    static void bfs(int N, int M){
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2];

        q.offer(new int[]{0, 0, 1, 0});
        visited[0][0][0] = true;


        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int w = cur[2];
            int b = cur[3]; //벽을 이미 부셨으면 1, 아니면 0

            if (r == map.length - 1 && c == map[0].length - 1) {
                answer = w;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = r + dr[i];
                int nextC = c + dc[i];

                if (nextR < 0 || nextC < 0 || nextR >= map.length || nextC >= map[0].length) continue; //범위가 넘으면 X
                if (visited[nextR][nextC][b]) continue;

                if (map[nextR][nextC] == 1) {
                    if (b == 0) { //벽인데, 벽을 부술 수 있다면
                        visited[nextR][nextC][1] = true;
                        q.offer(new int[]{nextR, nextC, w + 1, 1});
                    }
                } else {
                    visited[nextR][nextC][b] = true;
                    q.offer(new int[]{nextR, nextC, w + 1, b});
                }

            }
        }
    }
}