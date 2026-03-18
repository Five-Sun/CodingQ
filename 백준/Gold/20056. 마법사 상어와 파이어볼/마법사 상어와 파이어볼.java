import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;

    // 0, 1, 2, 3, 4, 5, 6, 7
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static class Fireball {
        int r, c, m, s, d;

        Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static List<Fireball> fireballs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireballs = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireballs.add(new Fireball(r, c, m, s, d));
        }

        while (K-- > 0) {
            simulate();
        }

        int answer = 0;
        for (Fireball f : fireballs) {
            answer += f.m;
        }

        System.out.println(answer);
    }

    static void simulate() {
        @SuppressWarnings("unchecked")
        ArrayList<Fireball>[][] board = new ArrayList[N][N];

        // 1. 이동
        for (Fireball f : fireballs) {
            int move = f.s % N;

            int nr = ((f.r + dx[f.d] * move) % N + N) % N;
            int nc = ((f.c + dy[f.d] * move) % N + N) % N;

            if (board[nr][nc] == null) {
                board[nr][nc] = new ArrayList<>();
            }
            board[nr][nc].add(new Fireball(nr, nc, f.m, f.s, f.d));
        }

        // 2. 합치기 / 나누기
        List<Fireball> next = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] == null) continue;

                int size = board[r][c].size();

                // 하나면 그대로 유지
                if (size == 1) {
                    next.add(board[r][c].get(0));
                    continue;
                }

                // 두 개 이상이면 합치고 분리
                int mSum = 0;
                int sSum = 0;
                boolean hasEven = false;
                boolean hasOdd = false;

                for (Fireball f : board[r][c]) {
                    mSum += f.m;
                    sSum += f.s;

                    if ((f.d & 1) == 0) hasEven = true;
                    else hasOdd = true;
                }

                int newM = mSum / 5;
                if (newM == 0) continue;

                int newS = sSum / size;

                int startDir = (hasEven && hasOdd) ? 1 : 0;
                for (int d = startDir; d < 8; d += 2) {
                    next.add(new Fireball(r, c, newM, newS, d));
                }
            }
        }

        fireballs = next;
    }
}