import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int H = maps.length;
        int W = maps[0].length();
        
        char[][] board = new char[H][W];
        int sr = 0, sc = 0, dr = 0, dc = 0;
        
        for(int r = 0; r < H; r++) {
            String row = maps[r];
            for(int c = 0; c < W; c++) {
                board[r][c] = row.charAt(c);
                if(board[r][c] == 'S') {
                    sr = r;
                    sc = c;
                }
            }
        }
        int answer = bfs(board, H, W, sr, sc);
        
        return answer;
    }
    
    private int bfs(char[][] map, int H, int W, int sr, int sc) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[H][W][2];
        visited[sr][sc][0] = true;
        q.offer(new int[]{sr, sc, 0, 0});
        
        int res = -1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int b = cur[2];
            int t = cur[3];
            
            if(map[r][c] == 'E' && b == 1) {
                return t;
            }
            
            for(int d=0; d<4; d++) {
                int nr = r + dx[d];
                int nc = c + dy[d];
                int nb = b;
                
                if(nr < 0 || nc < 0 || nr >= H || nc >= W) continue;
                if(map[nr][nc] == 'X') continue;
                if(map[nr][nc] =='L') {
                    nb = 1;
                }
                
                if(visited[nr][nc][nb]) continue;
                
                visited[nr][nc][nb] = true;
                q.offer(new int[]{nr, nc, nb, t + 1});
            }
        }
        return res;
    }
}