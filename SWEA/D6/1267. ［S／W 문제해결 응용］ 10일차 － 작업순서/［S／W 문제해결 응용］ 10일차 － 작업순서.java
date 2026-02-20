import java.util.*;
import java.io.*;

public class Solution {
    static int V, E;
    static int degrees[];
    static List<Integer>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        

        for(int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
        	V = Integer.parseInt(st.nextToken());
        	E = Integer.parseInt(st.nextToken());

            degrees = new int[V + 1];
            graph = new ArrayList[V + 1];
            for(int i=1; i<=V; i++) {
            	graph[i] = new ArrayList<>();
            }
            
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<E; i++) {
            	int first = Integer.parseInt(st.nextToken());
            	int next = Integer.parseInt(st.nextToken());
            	
            	graph[first].add(next);
            	degrees[next]++;
            }
            
            Queue<Integer> q = new ArrayDeque<>();
            boolean[] visited = new boolean[V + 1];
            for(int i=1; i<=V; i++) {
            	if(degrees[i] == 0) {
            		q.offer(i);
            		visited[i] = true;
            	}
            }
            
            sb.append("#").append(tc).append(" ");
            
            while(!q.isEmpty()) {
            	int cur = q.poll();
            	sb.append(cur).append(" ");
            	
            	for(int next : graph[cur]) {
            		if(!visited[next]) {
            			degrees[next]--;
            			if(degrees[next] == 0) {
                			q.offer(next);
                			visited[next] = true;
                		}
            		}
            	}
            }
            
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}