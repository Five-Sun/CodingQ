import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    // 좌, 하, 우, 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static int[][] dsx = {
            {1, -1, 2, -2, 0, 1, -1, 1, -1, 0},
            {-1, -1, 0, 0, 2, 0, 0, 1, 1, 1},
            {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
            {1, 1, 0, 0, -2, 0, 0, -1, -1, -1}
    };

    static int[][] dsy = {
            {1, 1, 0, 0, -2, 0, 0, -1, -1, -1},
            {1, -1, 2, -2, 0, 1, -1, 1, -1, 0},
            {-1, -1, 0, 0, 2, 0, 0, 1, 1, 1},
            {1, -1, 2, -2, 0, 1, -1, 1, -1, 0}
    };

    static int[] rate = {1, 1, 2, 2, 5, 7, 7, 10, 10};
    static int res = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int r = N / 2;
        int c = N / 2;
        int d = 0;
        int moveCnt = 1;
        int curCnt = 0;
        int turn = 0;

        while (true) {
            int nr = r + dx[d];
            int nc = c + dy[d];

            move(nr, nc, d);

            if (nr == 0 && nc == 0) break;

            curCnt++;
            if (curCnt == moveCnt) {
                curCnt = 0;
                turn++;
                d = (d + 1) % 4;
            }

            if (turn == 2) {
                turn = 0;
                moveCnt++;
            }

            r = nr;
            c = nc;
        }

        System.out.println(res);
    }

    static void move(int nr, int nc, int d) {
        int sand = map[nr][nc];
        int a = sand;

        for (int i = 0; i < 9; i++) {
            int sr = nr + dsx[d][i];
            int sc = nc + dsy[d][i];
            int ns = sand * rate[i] / 100;

            check(sr, sc, ns);
            a -= ns;
        }

        int ar = nr + dsx[d][9];
        int ac = nc + dsy[d][9];
        check(ar, ac, a);

        map[nr][nc] = 0;
    }

    static void check(int r, int c, int s) {
        if (r < 0 || c < 0 || r >= N || c >= N) {
            res += s;
        } else {
            map[r][c] += s;
        }
    }
}