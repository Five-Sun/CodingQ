import java.io.*;
import java.util.*;

class Solution {
    static int T, N, K;
    static char[] arr;
	public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = null;
    	StringBuilder sb = new StringBuilder();
    	
    	T = Integer.parseInt(br.readLine());
    	
    	for(int tc=1; tc<=T; tc++) {
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		K = Integer.parseInt(st.nextToken());
    		
    		int pwdLen = N / 4;
    		
    		arr = new char[N + pwdLen - 1];
    		String numStr = br.readLine();
    		for(int i=0; i<N; i++) {
    			arr[i] = numStr.charAt(i);
    		}
    		
    		for(int i=0; i<pwdLen - 1; i++) {
    			arr[N + i] = arr[i];
    		}
    		
    		Set<String> set = new TreeSet<>((a, b) ->  {
    			return Integer.parseInt(b, 16) - Integer.parseInt(a, 16);
    		});
    		
    		for(int i=0; i<N; i++) {
    			String key = "";
    			for(int j=0; j<pwdLen; j++) {
    				key += arr[i + j];
    			}
    			set.add(key);
    		}
    		
    		Iterator<String> it = set.iterator();
    		int cnt = 0;
    		int answer = 0;
    		while(it.hasNext()) {
    			cnt++;
    			String num = it.next();
    			if(cnt == K) {
    				answer = Integer.parseInt(num, 16);
    				break;
    			}
    		}
    		sb.append("#").append(tc).append(" ").append(answer).append("\n");
    	}
    	System.out.println(sb.toString());
    }
}