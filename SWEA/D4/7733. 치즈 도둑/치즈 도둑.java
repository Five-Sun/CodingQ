import java.util.*;
import java.io.*;

public class Solution {
    static int T, N, max, answer;
    static int[][] map;
    static boolean[][] visited;
    //상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 수
        T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            max = 0;
            answer = 1;
            map = new int[N][N];
            for(int r=0; r<N; r++) {
            	st = new StringTokenizer(br.readLine());
            	for(int c=0; c<N; c++) {
            		map[r][c] = Integer.parseInt(st.nextToken());
            		max = Math.max(max, map[r][c]);
            	}
            }
            
            for(int i=1; i<max; i++) {
            	int count = 0;
            	visited = new boolean[N][N];
            	for(int r=0; r<N; r++) {
                	for(int c=0; c<N; c++) {
                		if(!visited[r][c] && map[r][c] > i) {
                			bfs(r, c, i);
                			count++;
                		}
                	}
                }
            	answer = Math.max(answer, count);
            }
            
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }
    
    static void bfs(int sr, int sc, int k) {
    	Queue<int[]> q = new ArrayDeque<>();
    	visited[sr][sc] = true;
    	q.add(new int[] {sr, sc});
    	
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int r = cur[0];
    		int c = cur[1];
    		
    		for(int d=0; d<4; d++) {
    			int nr = r + dx[d];
    			int nc = c + dy[d];
    			
    			if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
    			if(visited[nr][nc]) continue;
    			if(map[nr][nc] <= k) continue;
    			
    			visited[nr][nc] = true;
    			q.offer(new int[] {nr, nc});
    		}
    	}
    } 
}