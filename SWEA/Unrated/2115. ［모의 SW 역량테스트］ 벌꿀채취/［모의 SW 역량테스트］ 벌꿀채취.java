import java.io.*;
import java.util.*;

public class Solution {
	static int[][] map;
	static int max;
	static int best;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	int M = Integer.parseInt(st.nextToken());
        	int C = Integer.parseInt(st.nextToken());
        	
        	max = 0;
        	map = new int[N][N];
        	for(int r=0; r<N; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c=0; c<N; c++) {
        			map[r][c] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	calc(0, new boolean[N][N][2], new int[2][M], N, M, C);
        	sb.append("#" + tc + " " + max + "\n");
        }
        System.out.println(sb.toString());
    }
    
    static void calc(int depth, boolean[][][] visited, int[][] out, int N, int M, int C) {
    	if(depth == 2) {
    		max = Math.max(max, price(out, M, C));
    		return;
    	}
    	
    	for(int r=0; r<N; r++) {
    		for(int c=0; c<N; c++) {
    			if(isPossible(r, c, depth, N, M, visited)) {
    				for(int i=0; i<M; i++) {
    		    		int nr = r;
    		    		int nc = c + i;
    		    		
    		    		visited[nr][nc][depth] = true;
    		    		out[depth][i] = map[nr][nc];
    		    	}
    				calc(depth + 1, visited, out, N, M , C);
    				for(int i=0; i<M; i++) {
    		    		int nr = r;
    		    		int nc = c + i;
    		    		
    		    		visited[nr][nc][depth] = false;
    		    	}
    			}
    		}
    	}
    }
    
    static int price(int[][] out, int M, int C) {
    	//최대 용량 만큼 담아서 값을 계산하는 로직 필요
    	int sum = 0;
    	for(int[] o : out) {
    		sum += best(o, M, C);
    	}
    	return sum;
    }
    
    static int best(int[] arr, int M, int C) {
    	best = 0;
    	dfs(arr, 0, M, C, 0 , 0);
    	return best;
    }
    
    static void dfs(int[] arr, int depth, int M, int C, int sum, int profit) {
    	if(sum > C) return;
    	if(depth == M) {
    		best = Math.max(best, profit);
    		return;
    	}
    	
    	dfs(arr, depth + 1, M, C , sum, profit);
    	
    	int w = arr[depth];
    	dfs(arr, depth + 1, M, C, sum + w, profit + w * w);
    }
    
    
    static boolean isPossible(int r, int c, int depth, int N, int M, boolean[][][] visited) {
    	for(int i=0; i<M; i++) {
    		int nr = r;
    		int nc = c + i;
    		
    		if(nr < 0 || nc < 0 || nr >= N || nc >= N) return false;
    		if(visited[nr][nc][0] || visited[nr][nc][1]) return false;
    	}
    	
    	return true;
    }
}