import java.util.*;

class Solution {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, -1};

    public int[] solution(int n) {
        int[][] arr = new int[n][n];
        int r = 0;
        int c = 0;
        int dir = 0;

        for (int num = 1; num <= n * (n + 1) / 2; num++) {
            arr[r][c] = num;
            int nr = r + dx[dir];
            int nc = c + dy[dir];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n || arr[nr][nc] != 0) {
                dir = (dir + 1) % 3;
            }

            r += dx[dir];
            c += dy[dir];
        }

        int [] answer = new int[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx++] = arr[i][j];
            }
        }
        
        return answer;
    }
}

/*
n ( n + 1) / 2 개의 숫자가 쓰임
숫자를 다 채워 넣는 문제인가? 해보자.
 */
