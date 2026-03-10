import java.io.*;
import java.util.*;

class Solution {
    static int T;
    static int[] p, r;
    static List<Edge> edges;
    
    static class Edge implements Comparable<Edge>{
    	int u, v;
    	long w;
    	
    	Edge(int u, int v, long w) {
    		this.u = u;
    		this.v = v;
    		this.w = w;
    	}
    	
    	@Override
    	public int compareTo(Edge o) {
    		return Long.compare(this.w, o.w);
    	}
    }
    static void makeSet(int n) {
        p = new int[n];
        r = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
            r[i] = 0;
        }
    }
    
    static int find(int x) {
		if(p[x] == x) return x;
		return p[p[x]] = find(p[x]);
	}
	
    static boolean union(int a, int b) {
		int pa = find(a), pb = find(b);
		
		if(pa == pb) return false;
		
		if(r[pa] > r[pb]) {
			p[pb] = pa;
		} else if(r[pa] < r[pb]) {
			p[pa] = pb;
		} else {
			p[pb] = pa;
			r[pa]++;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = null;
    	StringBuilder sb = new StringBuilder();
    	
    	T = Integer.parseInt(br.readLine());
    	
    	for(int tc=1; tc<=T; tc++) {
    		st = new StringTokenizer(br.readLine());
    		int N = Integer.parseInt(st.nextToken());
    		int M = Integer.parseInt(st.nextToken());
    		
    		makeSet(N + 1);
    		edges = new ArrayList<>();
    		
    		for(int i=0; i<M; i++) {
    			st = new StringTokenizer(br.readLine());
    			int u = Integer.parseInt(st.nextToken());
    			int v = Integer.parseInt(st.nextToken());
    			long w = Long.parseLong(st.nextToken());
    			
    			edges.add(new Edge(u, v, w));
    		}
    		
    		Collections.sort(edges);
    		
    		long mstCost = 0;
    		int count = 0;
    		
    		for(Edge e : edges) {
    			if(union(e.u, e.v)) {
    				mstCost += e.w;
    				count++;
    				if(count == N - 1) break;
    			}
    		}
    		
    		sb.append("#").append(tc).append(" ").append(mstCost).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}