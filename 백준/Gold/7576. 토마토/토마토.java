import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static int[] dy = {0, 0, 1, -1}; // 같은 층에서 상하
    static int[] dx = {1, -1, 0, 0}; // 같은 층에서 좌우

    static int M, N; // 가로, 세로, 높이
    static int[][] box; // 토마토 상자 (H층, N행, M열)
    static int[][] days; // 각 토마토가 익는 데 걸린 일수
    static Queue<int[]> q = new LinkedList<>(); // BFS를 위한 큐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        days =  new int[N][M];

        int unripeTomatoes = 0;

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            // M열을 순회 (x)
            for (int m = 0; m < M; m++) {
                box[n][m] = Integer.parseInt(st.nextToken());

                // 토마토 상태에 따라 초기화
                if (box[n][m] == 1) { // 익은 토마토는 큐에 추가 (BFS 시작점)
                    q.offer(new int[]{n, m}); // {z, y, x}
                    days[n][m] = 0; // 익은 토마토는 0일차에 익음
                } else if (box[n][m] == 0) { // 익지 않은 토마토
                    unripeTomatoes++; // 익지 않은 토마토 개수 카운트
                    days[n][m] = -1; // 아직 방문하지 않았음을 -1로 표시
                } else { // -1 (빈 칸)
                    days[n][m] = -2; // 빈 칸은 방문할 필요 없으므로 특정 값 (-2)으로 표시
                    // 또는 days 배열을 -1로만 초기화하고,
                    // BFS 탐색 시 box[h][n][m] == -1 이면 탐색 안 하도록 해도 됨
                }
            }
        }

        int maxDays = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int curY = current[0];
            int curX = current[1];

            maxDays = Math.max(maxDays, days[curY][curX]);

            // 6방향 탐색
            for (int i = 0; i < 4; i++) {
                int nextY = curY + dy[i];
                int nextX = curX + dx[i];

                // 범위 체크
                if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < M) {
                    // 익지 않은 토마토(0)이고 아직 방문하지 않았다면
                    if (box[nextY][nextX] == 0 && days[nextY][nextX] == -1) {
                        box[nextY][nextX] = 1; // 토마토 익음 처리
                        days[nextY][nextX] = days[curY][curX] + 1; // 일수 업데이트
                        q.offer(new int[]{nextY, nextX}); // 큐에 추가
                        unripeTomatoes--; // 익지 않은 토마토 개수 감소
                    }
                }
            }
        }

        // 모든 토마토가 익었는지 확인
        if (unripeTomatoes == 0) { // 모든 익지 않은 토마토가 익었다면
            System.out.println(maxDays);
        } else { // 아직 익지 않은 토마토가 남아있다면 (-1 출력)
            System.out.println(-1);
        }
    }
}