import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<i; j++) {
				if(arr[i] - arr[j] == M) {
					answer++;
					break;
				}
			}
		}
		System.out.println(answer);
	}
}