import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[] queen;
	static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	queen = new int[N];
        	answer = 0;
        	dfs(0);
        	sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }
   
    static void dfs(int row) {
    	if(row == N) {
    		answer++;
    		return;
    	}
    	
		for(int c=0; c<N; c++) {
			if(canPlace(row, c)) {
				queen[row] = c;
				dfs(row + 1);
			}
    	}
    }
    
    static boolean canPlace(int row, int col) {
    	for(int r=0; r<row; r++) {
    		int c = queen[r];
    		if(c == col) return false;
    		if(Math.abs(row - r) == Math.abs(col - c)) return false;
    	}
    
    	return true;
    }
}
