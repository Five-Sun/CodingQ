import java.util.*;

class Solution
{
    static int answer;
    public int solution(int [][]board)
    {
        answer = 0;
        int rLen = board.length;
        int cLen = board[0].length;

        int[][] dp = new int[rLen + 1][cLen + 1];
        dp[1][1] = board[0][0];
        for (int r = 1; r <= rLen; r++) {
            for (int c = 1; c <= cLen; c++) {
                if (board[r -1][c -1] == 1) {
                    dp[r][c] = Math.min(dp[r -1][c], Math.min(dp[r][c - 1], dp[r - 1][c - 1])) + 1;
                }
            }
        }

        for (int r = 1; r <= rLen; r++) {
            for (int c = 1; c <= cLen; c++) {
                if (answer < dp[r][c]) answer = dp[r][c];
            }
        }


        return answer * answer;
    }
}
