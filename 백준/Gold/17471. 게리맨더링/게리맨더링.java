import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] counts;
	static List<Integer>[] graph;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		counts = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			counts[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList[N];
		for(int i=0; i<N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<c; j++) {
				int k = Integer.parseInt(st.nextToken());
				graph[i].add(k -1);
			}
		}
		int max = Integer.MAX_VALUE;
		//1. 조합 탐색하기 ( 1 ~ N/2 )
		for(int mask = 1; mask < (1 << N); mask++) { // 0 ~ 111111
			if(Integer.bitCount(mask) <= N / 2) {
				if(isConnected(mask, true) && isConnected(mask, false)) {
					int sumA = 0;
					int sumB = 0;
					
					for(int i = 0; i < N; i++) {
						if((mask & (1 << i)) != 0) {
							sumA += counts[i];
						} else {
							sumB += counts[i];
						}
					}
					max = Math.min(max, Math.abs(sumA - sumB));
				}
			}
		}
		
		System.out.println(max == Integer.MAX_VALUE ? -1 : max);
	}

	/** 연결 여부 체크 메소드
	**  1인 그룹과 0인 그룹을 확인하기, bfs 두번
	**/
	static boolean isConnected(int mask, boolean inA) {
		boolean[] haveToVisit = new boolean[N];
		int start = -1;
		for(int i = 0; i < N; i++) {
			boolean isA = (mask & (1 << i)) != 0;
			if (isA == inA) {
				haveToVisit[i] = true;
				start = i;
			}
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(start);
		haveToVisit[start] = false;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next : graph[cur]) {
				if(haveToVisit[next]) {
					q.offer(next);
					haveToVisit[next] = false;
				}
			}
		}
		
		for(boolean remain : haveToVisit) {
			if(remain) {
				return false;
			}
		}
		
		return true;
	}
}
//1. 양방향 연결
//2. 인접한 구역이 없을 수도 있음에 주의
//3. 인구 차이의 최솟값이므로 비용 계산보단 구현에 가까운 거 같음
//4. 집합을 구해서 2보다 크면 탐색 불필요
//5. 이게 그래프의 연결 상태에 따라 구할 수 있는 집합이 달라지므로 단순 집합으로 계산하기 어려워 보임
//6. 1~N-1개를 고르는 조합과 반대 조합이 가능한지를 확인하며 계산한다?
//7. 빡구현인가?