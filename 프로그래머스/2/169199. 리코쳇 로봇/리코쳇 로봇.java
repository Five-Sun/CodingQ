import java.util.*;

class Solution {
    public int solution(String[] board) {
        int H = board.length;
        int W = board[0].length();

        char[][] map = new char[H][W];
        int sr = 0, sc = 0, dr = 0, dc = 0;
        for (int r = 0; r < H; r++) {
            String row = board[r];
            for (int c = 0; c < W; c++) {
                map[r][c] = row.charAt(c);

                if(map[r][c] == 'R') {
                    sr = r;
                    sc = c;
                } else if (map[r][c] == 'G') {
                    dr = r;
                    dc = c;
                }
            }
        }
        int answer = bfs(map, H, W, sr, sc, dr, dc);
        return answer;
    }

    private int bfs(char[][] map, int H, int W, int sr, int sc, int dr, int dc) {
        int res = -1;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc, 0});

        boolean[][] visited = new boolean[H][W];
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int cur[] = q.poll();
            int r = cur[0];
            int c = cur[1];
            int count = cur[2];

            if (r == dr && c == dc) {
                return count;
            }

            for (int d = 0; d < 4; d++) {
                int k = 1;
                while(true) {
                    int nr = r + dx[d] * k;
                    int nc = c + dy[d] * k;

                    if(nr < 0 || nc < 0 || nr >= H || nc >= W) break;
                    //장애물 만나면 즉시 종료
                    if (map[nr][nc] == 'D') break;
                    
                    k++;
                }
                //한칸 후진 필요
                k--;
                if (k == 0) continue;

                int nr = r + dx[d] * k;
                int nc = c + dy[d] * k;

                if(visited[nr][nc]) continue;

                q.offer(new int[]{nr, nc, count + 1});
                visited[nr][nc] = true;
            }
        }

        return res;
    }
}
//말 이동 최소 이동 => BFS
//상, 하, 좌, 우 이동 가능
//장애물 만나거나 벽까지 직진하는 조건으로 이동