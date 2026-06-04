import java.util.*;

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    class Node {
        int r, c, distance;

        Node(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }
    }
    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            answer[i] = isPossible(places[i]);
        }

        return answer;
    }

    private int isPossible(String [] place) {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (place[r].charAt(c) == 'P') {
                    if (!bfs(r, c, place)) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    private boolean bfs(int startR, int startC, String[] place) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];

        q.offer(new Node(startR, startC, 0));
        visited[startR][startC] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.distance == 2) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dx[d];
                int nc = cur.c + dy[d];

                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5 || visited[nr][nc]) {
                    continue;
                }

                char nextCell = place[nr].charAt(nc);
                int nextDist = cur.distance + 1;

                if (nextCell == 'X') {
                    continue;
                }

                if (nextCell == 'P') {
                    return false;
                }
                
                visited[nr][nc] = true;
                q.offer(new Node(nr, nc, nextDist));
            }
        }
        
        return true;
    }
}