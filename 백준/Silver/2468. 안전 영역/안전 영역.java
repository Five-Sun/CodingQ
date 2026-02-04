import java.util.*;
import java.io.*;

public class Main {
	static int[][] map;
	static int N;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int max = 0;
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[r][c]);
			}
		}
		int answer = 0;
		for(int h=0; h<=max; h++) {
			answer = Math.max(answer, check(h));
		}
		
		System.out.println(answer);
	}
	
	static int check(int limit) {
		int count = 0;
		boolean[][] visited = new boolean[N][N];
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c] > limit && !visited[r][c]) {
					bfs(r, c, limit, visited);
					count++;
				}
			}
		}
		return count;
	}
	
	static void bfs(int sr, int sc, int limit, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d=0; d<4; d++) {
				int nr = r + dx[d];
				int nc = c + dy[d];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] <= limit) continue;
				
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
	}

}