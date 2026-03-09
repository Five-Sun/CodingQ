import java.util.*;
import java.io.*;

class Solution {
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int T, N, answer, total;
	static char[][] map;
	static int[][] cnt;
	static boolean[][] visited;
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = null;
    	StringBuilder sb = new StringBuilder();
    	
    	T = Integer.parseInt(br.readLine());
    	
    	for(int tc = 1; tc <= T; tc++) {
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		map = new char[N][N];
    		cnt = new int[N][N];
    		visited = new boolean[N][N];
    		total = 0;
    		for(int r=0; r<N; r++) {
    			String row = br.readLine();
    			for(int c=0; c<N; c++) {
    				map[r][c] = row.charAt(c);
    				if(map[r][c] == '.') total++;
    			}
    		}
    		
    		answer = 0;
    		
    		for(int r=0; r<N; r++) {
    			Arrays.fill(cnt[r], -1);
    			for(int c=0; c<N; c++) {
    				if(map[r][c] == '.') {
    					cnt[r][c] = count(r, c);
    				}
    			}
    		}
    		
    		for(int r=0; r<N; r++) {
    			for(int c=0; c<N; c++) {
    				if(cnt[r][c] == 0) {
    					total -= bfs(r, c);
    				}
    			}
    		}
    		
    		sb.append("#").append(tc).append(" ").append(total).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
    
    static int count(int r, int c) {
    	int res = 0;
    	for(int d=0; d<8; d++) {
			int nr = r + dx[d];
			int nc = c + dy[d];
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
			if(map[nr][nc] == '*') {
				res++;
			}
    	}
    	return res;
    }
    
    static int bfs(int sr, int sc) {
    	int res = 0;
    	
    	Queue<int[]> q = new ArrayDeque<int[]>();
    	q.offer(new int[] {sr, sc});
    	visited[sr][sc] = true;
    	
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int r = cur[0];
    		int c = cur[1];

    		for(int d=0; d<8; d++) {
    			int nr = r + dx[d];
    			int nc = c + dy[d];
    			
    			if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
    			if(cnt[nr][nc] == -1) continue;
    			if(visited[nr][nc]) continue;
    			
    			if(cnt[nr][nc] == 0) q.offer(new int[] {nr, nc});
    			visited[nr][nc] = true;
    			res++;
    		}
    	}
    	return res;
    }
}