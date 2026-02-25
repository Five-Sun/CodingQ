import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int idx = 1;
		while((N = Integer.parseInt(br.readLine())) != 0) {
			map = new int[N][N];
			
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = dijkstra();
			sb.append("Problem " + idx++ + ": " + answer + "\n");
		}

		System.out.println(sb.toString());
	}
	
	static int dijkstra() {
		int INF = 1_000_000_000;
		int[][] costs = new int[N][N];
		for(int[] row : costs) {
			Arrays.fill(row, INF);
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		pq.offer(new int[] {0, 0, map[0][0]});
		costs[0][0] = map[0][0];
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int r = cur[0];
			int c = cur[1];
			int cost = cur[2];
			
			if(cost > costs[r][c]) continue;
			
			if(r == N - 1 && c == N - 1) {
				return cost;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dx[d];
				int nc = c + dy[d];
				
				if(!isPossible(nr, nc)) continue;
				
				int nCost = cost + map[nr][nc];
				if(nCost >= costs[nr][nc]) continue;
				
				pq.offer(new int[] {nr, nc, nCost});
				costs[nr][nc] = nCost;
			}
		}
		
		return costs[N - 1][N - 1];
	}

	static boolean isPossible(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N); 
	}
}
