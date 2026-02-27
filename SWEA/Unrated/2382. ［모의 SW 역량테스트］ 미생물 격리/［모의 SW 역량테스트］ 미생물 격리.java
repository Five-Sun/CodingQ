

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Solution {
	static class Microbe{
		int y;
		int x;
		int size;
		int dir;
		
		public Microbe(int y, int x, int size, int dir) {
			this.y=y;
			this.x=x;
			this.size=size;
			this.dir=dir;
		}
	}
	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,-1,1,0,0};
	static int[] reverse = {0,2,1,4,3};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			List<Microbe> list = new ArrayList<>();
			
			for(int k=0;k<K;k++){
				st = new StringTokenizer(br.readLine());
				Microbe m = new Microbe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				list.add(m);
			}
			
			//주어진 시간만큼 반복
			for(int m=0;m<M;m++) {
				//각 미생물 이동 시키기
				for(Microbe mic : list) {
					mic.y = mic.y+dy[mic.dir];
					mic.x = mic.x+dx[mic.dir];
				}
				
				for(int i=list.size()-1;i>=0;i--) {
					Microbe mic = list.get(i);
					//미생물이 약품존에 위치한 경우 
					if(mic.y==0 || mic.y==N-1 || mic.x==0 || mic.x==N-1) {
						//방향 바꾸기
						mic.dir = reverse[mic.dir];
						//size 절반으로 줄이기(소수점 버림)
						mic.size = mic.size/2;
					}
					if(mic.size==0) {
						list.remove(i);
					}
				}
				
				//만약 미생물의 위치가 동일한 경우 더 큰 군집으로 편입 시키기			
				list.sort((mic1, mic2) -> {
					if(mic1.y != mic2.y) return mic1.y - mic2.y;
					return mic1.x - mic2.x;
				});
				List<Microbe> merged = new ArrayList<>();
				int i=0;
				while(i<list.size()) {
					int y = list.get(i).y;
					int x = list.get(i).x;
					
					int sum = 0;
					int maxSize = 0;
					int keepDir = 0;
					
					while(i<list.size() && list.get(i).y == y && list.get(i).x == x) {
						Microbe cur = list.get(i);
						sum += cur.size;
						
						if(cur.size>maxSize) {
							maxSize = cur.size;
							keepDir = cur.dir;
						}
						i++;
					}
					merged.add(new Microbe(y, x, sum, keepDir));
				}
				list = merged;
			}
			
			int result = 0;
			for(int i=0;i<list.size();i++) {
				result+=list.get(i).size;
			}
			System.out.println("#"+t+" "+result);
			
		}
	}

}
