import java.io.*;
import java.util.*;

public class Solution {
	static ArrayList<int[]>[] graph;
	static int N;
	static ArrayList<int[]> result = new ArrayList<>();
	static ArrayList<Condition> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken()); //N개의 우리
        	int X = Integer.parseInt(st.nextToken()); //X마리 이하의 햄스터
        	int M = Integer.parseInt(st.nextToken()); //M개의 기록

        	boolean flag = true;
        	result = new ArrayList<>();
        	list = new ArrayList<>();
        	for(int i=0; i<M; i++) {
        		st = new StringTokenizer(br.readLine());
        		int l = Integer.parseInt(st.nextToken()) - 1;
        		int r = Integer.parseInt(st.nextToken()) - 1;
        		int s = Integer.parseInt(st.nextToken());
        		
        		if(l < 0 || r < 0 || l >= N || r >= N || l > r) {
        			flag = false;
        			break;
        		}
        		
        		list.add(new Condition(l, r, s));
        	}
        	
        	if(flag) {
        		permu(N, X, 0, new int[N]);
            	Collections.sort(result, (a, b) ->  {
            		int s1 = sum(a);
            		int s2 = sum(b);
            		
            		int c = Integer.compare(s2, s1);
            		if(c != 0) return c;
            		
            		for(int i=0; i<N; i++) {
            			if(a[i] != b[i]) return Integer.compare(a[i], b[i]);
            		}
            		
            		return 0;
            	});
        	}
        	
        	sb.append("#" + tc + " ");
        	if(result.isEmpty()) {
        		sb.append(-1 + "\n");
        	} else {
        		for(int n : result.get(0)) {
        			sb.append(n + " ");
        		}
        		sb.append("\n");
        	}
        }
        System.out.println(sb.toString());
    }
    
    static int sum(int[] a) {
    	int s = 0;
    	for(int v: a) s += v;
    	return s;
    }
    
    static void permu(int N, int X, int depth, int[] out) {
    	if(depth == N) {
    		if(isPossible(out, X)) {
    			result.add(out.clone());
    		}
    		return;
    	}
    	
    	for(int i=0; i<=X; i++) {
    		out[depth] = i;
    		permu(N, X , depth + 1, out);
    	}
    }
    
    static boolean isPossible(int[] out, int X) {
    	int max = 0;
    	for(int num : out) {
    		max = Math.max(max, num);
    	}
    	
    	if(max > X) return false;
    	
    	for(Condition c : list) {
    		int sum = 0;
    		for(int i=c.l; i<=c.r; i++) {
    			sum += out[i];
    		}
    		if(sum != c.s) return false;
    	}
    	return true;
    }
    static class Condition {
    	int l, r, s;
    	
    	Condition(int l, int r, int s) {
    		this.l = l;
    		this.r = r;
    		this.s = s;
    	}
    }
}