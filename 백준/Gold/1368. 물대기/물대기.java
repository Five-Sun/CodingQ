import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = null;
    	
    	int N = Integer.parseInt(br.readLine());
    	
    	long[] well = new long[N]; //우물 비용
    	
    	for(int i=0; i<N; i++) {
    		well[i] = Integer.parseInt(br.readLine());
    	}
    	
    	long[][] cost = new long[N][N];
    	
    	for(int r=0; r<N; r++) {
    		st = new StringTokenizer(br.readLine());
    		for(int c=0; c<N; c++) {
    			cost[r][c] = Long.parseLong(st.nextToken());
    		}
    	}
    	
    	long[] minCost = new long[N];
    	boolean[] used = new boolean[N];
    	
    	//초기 후보를 세팅
    	for(int i=0; i<N; i++) minCost[i] = well[i];
    	
    	long ans = 0;
    	
    	for(int i=0; i<N; i++) {
    		int u = -1;
    		long best = Long.MAX_VALUE;
    		for(int j=0; j<N; j++) {
    			if(!used[j] && minCost[j] < best) {
    				best = minCost[j];
    				u = j;
    			}
    		}
    		
    		used[u] = true;
    		ans += best;
    		
    		for(int k=0; k<N; k++) {
    			if(!used[k] && cost[u][k] != 0 && cost[u][k] < minCost[k]) {
    				minCost[k] = cost[u][k];
    			}
    		}
    	}
    	
    	System.out.println(ans);
    	
    }
}
//1. N개의 논, i 번째 논에 울물을 팔 때 드는 비용 W
//2. i번째 논과 j번째 논을 연결하는데 드는 비용