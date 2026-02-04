import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine()); //1000 ~ 9999
		
		dfs(2, 1);
        dfs(3, 1);
        dfs(5, 1);
        dfs(7, 1);
        
		System.out.println(sb.toString());
	}
	
	static void dfs(int num, int len) {
		if(len == N) {
			sb.append(num + "\n");
			return;
		}
		
		for(int i=1; i<10; i+=2) {
			int next = num * 10 + i;
			if(isPrime(next)) {
				dfs(next, len + 1);
			}
		}
	}
	
	static boolean isPrime(int num) {
		if(num < 2) return false;
		for(int i=2; i*i<=num; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
}