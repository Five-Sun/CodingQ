import java.util.*;

class Solution {
    static int INF = 100;
    static ArrayList<Integer>[] graph;
    public int solution(int n, int[][] wires) {
        int answer = INF;
        
        //1. 그래프 초기 생성
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        //2. 그래프 연결정보 저장(양방향)
        for(int i = 0; i < wires.length; i++) {
            int[] wire = wires[i];
            int a = wire[0];
            int b = wire[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        //3. 하나씩 간선을 끊어보면서 계산하기
        for(int i = 0; i < wires.length; i++) {
            int[] wire = wires[i];
            int a = wire[0];
            int b = wire[1];
            
            graph[a].remove(Integer.valueOf(b));
            graph[b].remove(Integer.valueOf(a));
            
            //탐색
            int count = bfs(1, new boolean[n + 1]);
            int diff = Math.abs((n - count) - count);
            answer = Math.min(answer, diff);
            
            //복원
            graph[a].add(b);
            graph[b].add(a);
        }
        
        return answer;
    }
    
    public static int bfs(int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        
        int count = 1;
        visited[start] = true;
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(int next : graph[cur]) {
                if(!visited[next]) {
                    visited[next] = true;
                    count++;
                    q.offer(next);
                }
            }
        }
        
        return count;
    }
}