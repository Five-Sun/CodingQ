import java.util.*;

class Solution {
    static int answer;
    static boolean[] cols;
    static boolean[] diag1;
    static boolean[] diag2;
    public int solution(int n) {
        answer = 0;
        cols = new boolean[n];
        diag1 = new boolean[2 * n - 1]; //우상향
        diag2 = new boolean[2 * n - 1]; //좌상향

        dfs(0, n);

        return answer;
    }

    private void dfs(int r, int n) {
        if (r == n) {
            answer++;
            return;
        }

        for (int c = 0; c < n; c++) {
            if (cols[c] || diag1[r - c + (n - 1)] || diag2[r + c]) continue;

            cols[c] = diag1[r - c + n - 1] = diag2[r + c] = true;

            dfs(r + 1, n);

            cols[c] = diag1[r - c + n - 1] = diag2[r + c] = false;
        }
    }
}