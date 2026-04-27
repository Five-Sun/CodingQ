import java.util.*;

class Solution {
    static int memo[][];
    static boolean[][] blocked;
    static final int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        memo = new int[n + 1][m + 1];
        blocked = new boolean[n + 1][m + 1];
        
        for(int[] puddle: puddles) {
            int c = puddle[0];
            int r = puddle[1];
            blocked[r][c] = true;
        }
        
         for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return topDown(n, m, n, m);
    }
    
    public static int topDown(int r, int c, int n, int m) {
        if(r < 1 || c < 1) return 0;
        if(blocked[r][c]) return 0;
        
        if(r == 1 && c == 1) return 1;
        
        if(memo[r][c] != -1) return memo[r][c];
        
        memo[r][c] = (topDown(r - 1, c, n, m) + topDown(r, c - 1, n, m)) % MOD;
        return memo[r][c];
    }
}

/**
* 오른쪽과 아래로만 움직일 수 있다는 것에 집중,
* 하향식으로 왼쪽과 위쪽에서 들어오는 것만 생각해라.
*/