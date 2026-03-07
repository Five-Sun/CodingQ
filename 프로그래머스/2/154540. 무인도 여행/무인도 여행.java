import java.util.*;

class Solution {
    int sum;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    public int[] solution(String[] maps) {
        int H = maps.length;
        int W = maps[0].length();
        char[][] map = new char[H][W];
        for (int r = 0; r < H; r++) {
            String row = maps[r];
            for (int c = 0; c < W; c++) {
                map[r][c] = row.charAt(c);
            }
        }

        boolean[][] visited = new boolean[H][W];
        List<Integer> list = new ArrayList<>();
        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                if (map[r][c] != 'X' && !visited[r][c]) {
                    sum = 0;
                    visited[r][c] = true;
                    sum += map[r][c] - '0';
                    dfs(map, H, W, r, c, visited);
                    list.add(sum);
                }
            }
        }
        int[] answer = list.stream().sorted().mapToInt(Integer::intValue).toArray();
        if(answer.length == 0) return new int[]{-1};
        return answer;
    }

    private void dfs(char[][] map, int h, int w, int sr, int sc, boolean[][] visited) {
        for (int d = 0; d < 4; d++) {
            int nr = sr + dx[d];
            int nc = sc + dy[d];

            if (nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
            if(map[nr][nc] == 'X') continue;
            if(visited[nr][nc]) continue;

            visited[nr][nc] = true;
            sum += map[nr][nc] - '0';
            dfs(map, h, w, nr, nc, visited);
        }
    }
}
//출발 가능한 모든 지점에서 bfs탐색하면 되는 거 아녀?
//단, 이미 방문한 곳 X, 숫자 아닌 곳 X