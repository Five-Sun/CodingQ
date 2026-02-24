import java.io.*;
import java.util.*;

public class Main {
	static int K, W, H;
	static int[][] map;
	static int[] monkeyDx = {-1, 0, 1, 0};
	static int[] monkeyDy = {0, -1, 0, 1};
	static int[] horseDx = {-2, -2, -1, 1, 2, 2, -1, 1};
	static int[] horseDy = {-1, 1, -2, -2, -1, 1, 2, 2};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		for(int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = -1;
		
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[H][W][K + 1];
		q.offer(new int[] {0, 0, 0, 0});
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int k = cur[2];
			int cnt = cur[3];
			
			if(r == H - 1 && c == W - 1) {
				answer = cnt;
				break;
			}
			
			for(int d=0; d<4; d++) {
				int nr = r + monkeyDx[d];
				int nc = c + monkeyDy[d];
				
				if(!isPossible(nr, nc)) continue;
				if(visited[nr][nc][k]) continue;
				
				q.offer(new int[] {nr, nc, k, cnt + 1});
				visited[nr][nc][k] = true;
			}
			
			if(k < K) {
				for(int d=0; d<8; d++) {
					int nr = r + horseDx[d];
					int nc = c + horseDy[d];
					
					if(!isPossible(nr, nc)) continue;
					if(visited[nr][nc][k + 1]) continue;
					
					q.offer(new int[] {nr, nc, k + 1, cnt + 1});
					visited[nr][nc][k + 1] = true;
				}
			}
		}
		
		System.out.println(answer);
	}

	static boolean isPossible(int r, int c) {
		if(r < 0 || c < 0 || r >= H || c >= W) return false;
		if(map[r][c] != 0) return false;
		return true;
	}
}
