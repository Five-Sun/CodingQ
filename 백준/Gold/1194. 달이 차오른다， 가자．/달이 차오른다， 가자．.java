import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static char[][] map;
	static StringBuilder sb = new StringBuilder();
	static int answer = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		int sr = 0, sc = 0;
		for(int r=0; r<N; r++) {
			String temp = br.readLine();
			for(int c=0; c<M; c++) {
				map[r][c] = temp.charAt(c);
				if(map[r][c] == '0') {
					sr = r;
					sc = c;
				}
			}
		}
		
		bfs(sr, sc);
		System.out.println(answer);
	}
	
	static void bfs(int sr, int sc) {
		boolean [][][] visited = new boolean[N][M][64];
		Queue<int[]> q = new ArrayDeque<>();
		visited[sr][sc][0] = true;
		q.offer(new int[] {sr, sc, 0, 0});
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int mask = cur[2];
			int count = cur[3];
			
			if(map[r][c] == '1') {
				answer = count;
				return;
			}
			
			for(int d=0; d<4; d++) {
				int nr = r + dx[d];
				int nc = c + dy[d];
				int nMask = mask;
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if(map[nr][nc] == '#') continue;
				
				if('a' <= map[nr][nc] && map[nr][nc] <= 'f') {
					nMask = mask | (1 << (map[nr][nc] - 'a'));
				}
				
				if('A' <= map[nr][nc] && map[nr][nc] <= 'F') {
					if((nMask & (1 << (map[nr][nc] - 'A'))) == 0) continue; 
				}
				
				if(!visited[nr][nc][nMask]) {
					visited[nr][nc][nMask] = true;
					q.offer(new int[] {nr, nc, nMask, count + 1});
				}
			}
		}
	}
	
}