import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;
	static int N, W, H, result;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			result = Integer.MAX_VALUE;
			map = new int[H][W];
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bomb(N);
			System.out.println("#"+t+" "+result);
		}
	}
	private static void bomb(int cnt) {
		if(cnt == 0) {
			int count = blockCount();
			result = Math.min(count, result);
			return;
		}
		
		int[][] backup = new int[H][W];
		for (int i = 0; i < H; i++) {
		    backup[i] = map[i].clone(); // 각 행(1차원 배열)을 각각 복제
		}
		
		//각 열마다 폭탄 투하 하기
		for(int c=0;c<W;c++) {
			//현재 열의 가장 상단에 위치한 벽돌 찾기
			int y=0;
			for(int r=0;r<H;r++) {
				if(map[r][c]>0) {
					y=r;
					break;
				}
			}
			
			//시작 위치부터 4가지 방향으로 벽돌 깨기. bfs
			Queue<int[]> queue = new ArrayDeque<>();
			boolean[][] visited = new boolean[H][W];
			queue.offer(new int[] {y,c});
			visited[y][c] = true;
			while(!queue.isEmpty()) {
				int[] position = queue.poll();
				int blockNum = map[position[0]][position[1]];
				for(int d=0;d<4;d++) {
					for(int i=0;i<blockNum;i++) {
						int nr = position[0]+dy[d]*i;
						int nc = position[1]+dx[d]*i;
						if(nr<0 || nr>=H || nc<0 || nc>=W || visited[nr][nc]) continue;
						if(map[nr][nc] !=0) {
							queue.offer(new int[] {nr, nc});
						}
						visited[nr][nc] = true;
					}
				}
			}
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					if(visited[i][j]) {
						map[i][j] =0;
					}
				}
			}
			
			//벽돌 내리기
			moveBlock();
			
			//다음 폭탄  투하 
			bomb(cnt-1);
			
			//기존 배열로 돌려 놓기
			for (int i = 0; i < H; i++) {
	            map[i] = backup[i].clone();
	        }
		}
		
		
		
	}
	//벽돌 내리기
	static void moveBlock() {
		Deque<Integer> queue = new ArrayDeque<>();
		for(int c=0;c<W;c++) {
			for(int r=0;r<H;r++) {
				if(map[r][c]>0) {
					queue.offer(map[r][c]);
					map[r][c] = 0;
				}
			}
			int r=H-1;
			int size = queue.size();
			for(int i=0;i<size;i++) {
				map[r--][c] = queue.pollLast();
			}
		}
	}
	
	
	//전체 맵의 벽돌 개수 구하기
	static int blockCount() {
		int result = 0;
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++){
				if(map[i][j]>0) result++;
			}
		}
		return result;
	}

}
