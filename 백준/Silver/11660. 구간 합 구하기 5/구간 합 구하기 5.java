import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] arr = new int[N + 1][N + 1];
     
        for(int i=1; i<=N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=1; j<=N; j++) {
        		int num = Integer.parseInt(st.nextToken());
        		arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + num;
        	}
        }
        
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int sr = Integer.parseInt(st.nextToken());
        	int sc = Integer.parseInt(st.nextToken());
        	int dr = Integer.parseInt(st.nextToken());
        	int dc = Integer.parseInt(st.nextToken());
        	
        	
        	sb.append(arr[dr][dc] - arr[sr - 1][dc] - arr[dr][sc - 1] + arr[sr - 1][sc - 1] + "\n");
        }
        System.out.println(sb);
    }
}
