import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] fishNum = new int[4][4];
        int[][] fishDir = new int[4][4];

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                fishNum[i][j] = Integer.parseInt(st.nextToken());
                fishDir[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        // 상어 시작 (0,0)
        int firstFish = fishNum[0][0];
        int sharkDir = fishDir[0][0];
        fishNum[0][0] = 0; // 먹음

        dfs(fishNum, fishDir, 0, 0, sharkDir, firstFish);

        System.out.println(answer);
    }

    static void dfs(int[][] fishNum, int[][] fishDir,
                    int sx, int sy, int sdir, int sum) {

        answer = Math.max(answer, sum);

        moveFish(fishNum, fishDir, sx, sy);

        for (int step = 1; step <= 3; step++) {
            int nx = sx + dx[sdir] * step;
            int ny = sy + dy[sdir] * step;

            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) break;
            if (fishNum[nx][ny] == 0) continue;

            // 상태 복사
            int[][] newFishNum = copy(fishNum);
            int[][] newFishDir = copy(fishDir);

            int eaten = newFishNum[nx][ny];
            int ndir = newFishDir[nx][ny];

            newFishNum[nx][ny] = 0;

            dfs(newFishNum, newFishDir, nx, ny, ndir, sum + eaten);
        }
    }

    static void moveFish(int[][] fishNum, int[][] fishDir, int sx, int sy) {

        for (int num = 1; num <= 16; num++) {

            int x = -1, y = -1;

            // 물고기 위치 찾기
            find:
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (fishNum[i][j] == num) {
                        x = i; y = j;
                        break find;
                    }
                }
            }

            if (x == -1) continue;

            int dir = fishDir[x][y];

            for (int k = 0; k < 8; k++) {
                int nd = (dir + k) % 8;
                int nx = x + dx[nd];
                int ny = y + dy[nd];

                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
                if (nx == sx && ny == sy) continue;

                int target = fishNum[nx][ny];
                int targetDir = fishDir[nx][ny];

                fishNum[nx][ny] = num;
                fishDir[nx][ny] = nd;

                fishNum[x][y] = target;
                fishDir[x][y] = targetDir;

                break;
            }
        }
    }


    static int[][] copy(int[][] arr) {
        int[][] res = new int[4][4];
        for (int i = 0; i < 4; i++)
            res[i] = arr[i].clone();
        return res;
    }
}
