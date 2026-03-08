import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        // dist[v] = x에서 v까지 최소 연산 횟수
        int[] dist = new int[y + 1];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        dist[x] = 0;
        q.offer(x);

        while (!q.isEmpty()) {
            int cur = q.poll();
            int step = dist[cur];

            if (cur == y) return step;

            int a = cur + n;
            int b = cur * 2;
            int c = cur * 3;

            if (a <= y && dist[a] == -1) {
                dist[a] = step + 1;
                q.offer(a);
            }
            if (b <= y && dist[b] == -1) {
                dist[b] = step + 1;
                q.offer(b);
            }
            if (c <= y && dist[c] == -1) {
                dist[c] = step + 1;
                q.offer(c);
            }
        }
        return -1;
    }
}