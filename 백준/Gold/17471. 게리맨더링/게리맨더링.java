import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] numOfPeple = new int[N+1];
		int[][] adjlist = new int[N+1][N+1];
		
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		int result = Integer.MAX_VALUE;
		for(int i=1;i<N+1;i++) {
			  numOfPeple[i] = Integer.parseInt(st.nextToken());
			  sum+=numOfPeple[i];
		}
		for(int i=1;i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			for(int j=0;j<count;j++) {
				adjlist[i][Integer.parseInt(st.nextToken())] = 1;
			}
		}
		
		//구역1,2에 속하는 부분집합 구하기
		for(int i=1;i<Math.pow(2, N)-1;i++) {
			boolean[] pick1 = new boolean[N+1];
			boolean[] pick2 = new boolean[N+1];
			for(int j=0;j<N;j++) {
				if((i&(1<<j))!=0) {
					pick1[j+1] = true;
				}else {
					pick2[j+1] = true;
				}
			}
			
			//각 부분 집합이 연결되어 있는지 bfs로 탐색
			//1번 구역 확인
			Queue<Integer> queue = new ArrayDeque<>();
			boolean[] visited = new boolean[N+1];
			for(int j=1;j<N+1;j++) {
				if(pick1[j]) {
					queue.offer(j);
					visited[j] = true;
					break;
				}
			}
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				for(int j=1;j<N+1;j++) {
					if(adjlist[cur][j] == 1 && !visited[j] && pick1[j]) {
						queue.offer(j);
						visited[j] = true;
					}
				}
			}
			//구역 1이 모두 연결되어 있는지 확인하기
			boolean pick1Link = true;
			for(int j=1;j<N+1;j++) {
				if(pick1[j]!=visited[j]) {
					pick1Link = false;
					break;
				}
			}
			
			//2번 구역 확인
			visited = new boolean[N+1];
			for(int j=1;j<N+1;j++) {
				if(pick2[j]) {
					queue.offer(j);
					visited[j] = true;
					break;
				}
			}
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				for(int j=1;j<N+1;j++) {
					if(adjlist[cur][j] == 1 && !visited[j] && pick2[j]) {
						queue.offer(j);
						visited[j] = true;
					}
				}
			}
			
			boolean pick2Link = true;
			for(int j=1;j<N+1;j++) {
				if(pick2[j]!=visited[j]) {
					pick2Link = false;
					break;
				}
			}
			
			//만약 연결되어 있다면?각 구역의 인구수의 합을 구하고 차가 작으면 result를 갱신한다.
			int numOfPick1 = 0;
			if(pick1Link && pick2Link) {
				for(int j=1;j<N+1;j++) {
					if(pick1[j]) {
						numOfPick1+=numOfPeple[j];
					}
				}
				result = Math.min(result, Math.abs(sum-numOfPick1 - numOfPick1));
			}
			
		}
		if(result == Integer.MAX_VALUE) {
			result = -1;
		}
		System.out.println(result);
	}

}
