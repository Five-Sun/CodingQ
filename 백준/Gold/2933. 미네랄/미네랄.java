import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static char[][] cave;
    static ArrayList<Point> p = new ArrayList<>();
    static int R, C;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cave = new char[R][C];
        for (int r = 0; r < R; r++) {
            String temp = br.readLine();
            for (int c = 0; c < C; c++) {
                cave[r][c] = temp.charAt(c);
            }
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int H = Integer.parseInt(st.nextToken());

            if (i % 2 == 0) { //왼쪽에서 던지기
                for (int j = 0; j < C; j++) {
                    if (cave[R - H][j] == 'x') { //미네랄을 마주치면
                        cave[R - H][j] = '.';
                        //상하좌우 탐색 시작
                        for (int d = 0; d < 4; d++) {
                            int nextR = R - H + dr[d];
                            int nextC = j + dc[d];

                            if (nextR < 0 || nextC < 0 || nextR >= R || nextC >= C) continue;
                            if (cave[nextR][nextC] == '.') continue;

                            bfs(nextR, nextC);
                            fall();
                        }
                        break;
                    }
                }
            } else { //오른쪽에서 던지기
                for (int j = C - 1; j >= 0; j--) {
                    if (cave[R - H][j] == 'x') {
                        cave[R - H][j] = '.';
                        //상하좌우 탐색 시작
                        for (int d = 0; d < 4; d++) {
                            int nextR = R - H + dr[d];
                            int nextC = j + dc[d];

                            if (nextR < 0 || nextC < 0 || nextR >= R || nextC >= C) continue;
                            if (cave[nextR][nextC] == '.') continue;

                            bfs(nextR, nextC);
                            fall();
                        }
                        break;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                sb.append(cave[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void fall() {
        if (!p.isEmpty()) {
            for (Point point : p) {
                int r = point.r;
                int c = point.c;
                cave[r][c] = '.';
            }

            int fall = R;

            for (Point point : p) {
                int r = point.r;
                int c = point.c;
                int dist = 0;

                while (true) {
                    int nr = r + dist + 1;
                    if (nr >= R) break; //바닥
                    if (cave[nr][c] == 'x') break;//미네랄
                    dist++;
                }
                fall = Math.min(fall, dist);
            }

            for (Point point : p) {
                cave[point.r + fall][point.c] = 'x';
            }

            p.clear();
        }
    }

    static void bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));

        boolean[][] visited = new boolean[R][C];
        visited[r][c] = true;

        ArrayList<Point> temp = new ArrayList<>();
        temp.add(new Point(r, c));

        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (cur.r == R - 1) {
                temp.clear();
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nextR = cur.r + dr[d];
                int nextC = cur.c + dc[d];

                if (nextR < 0 || nextC < 0 || nextR >= R || nextC >= C) continue;
                if (cave[nextR][nextC] == '.') continue;
                if (visited[nextR][nextC]) continue;

                Point next = new Point(nextR, nextC);
                q.offer(next);
                visited[nextR][nextC] = true;
                temp.add(next);
            }
        }
        if (!temp.isEmpty()) {
            p.addAll(temp);
        }
    }

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
//던진 높이에서 미네랄 찾고, 제거하기
//제거하고, 상하좌우를 확인해서 클러스트 찾기? BFS/ DFS
//바닥과 안붙어 있는 클러스트 내리기?