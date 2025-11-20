import java.io.*;
import java.util.*;

class Main {
    static int[][] map;
    static int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 0은 빈칸, 1은 벽, 2는 바이러스
        // 바이러스는 상하좌우로 퍼져 나간다.
        // 벽을 3개 세워서 바이러스가 퍼지지 않는 최대값을 구해라.
        // 바이러스의 위치를 Queue에 저장해둔다.
        // 안전지대의 구역을 미리 구해두고, 퍼져 나가는 만큼 -- 해주면 계산 가능
        // 벽을 놓을 위취는 map이 0인 경우에만 가능하고, 3개를 순서대로 다 세워보고 계산해봐야할 거 같다.
        // 벽을 세울 수 있는 위치도 다 기억해두고 3중 for문으로 돌려야할 거 같아.
        // N,M이 최대 8이라서 가능할 거 같아.

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        ArrayList<int[]> viruses = new ArrayList<>(); //바이러스 위치
        ArrayList<int[]> walls = new ArrayList<>(); //벽 세우기 가능한 위치
        int safeZone = 0; //최초 안전지대 계산용
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                int n = Integer.parseInt(st.nextToken());
                map[r][c] = n;
                if (n == 0) {
                    safeZone++;
                    walls.add(new int[]{r, c});
                } else if (n == 2) {
                    viruses.add(new int[]{r, c});
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < walls.size() - 2; i++) {
            for (int j = i + 1; j < walls.size() - 1; j++) {
                for (int k = j + 1; k < walls.size(); k++) {
                    makeWall(walls.get(i));
                    makeWall(walls.get(j));
                    makeWall(walls.get(k));
                    safeZone -= 3;
                    answer = Math.max(answer, bfs(N, M, viruses, safeZone));
                    safeZone += 3;
                    deleteWall(walls.get(i));
                    deleteWall(walls.get(j));
                    deleteWall(walls.get(k));
                }
            }
        }

        System.out.println(answer);
    }

    static int bfs(int N, int M, ArrayList<int[]> viruses, int count) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        for (int[] virus : viruses) {
            q.offer(virus);
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextR = r + dr[i];
                int nextC = c + dc[i];

                if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) continue;
                if (map[nextR][nextC] != 0) continue;
                if (visited[nextR][nextC]) continue;

                visited[nextR][nextC] = true;
                q.offer(new int[]{nextR, nextC});
                count--;
            }
        }
        return count;
    }

    static void makeWall(int[] arr) {
        map[arr[0]][arr[1]] = 1;
    }

    static void deleteWall(int[] arr) {
        map[arr[0]][arr[1]] = 0;
    }

}