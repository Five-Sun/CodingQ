import java.util.*;

class Solution {
    static int[][] map;

    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];

        int num = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                map[r][c] = num++;
            }
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(queries[i]);
        }

        return answer;
    }

    private int rotate(int[] query) {
        int x1 = query[0] - 1;
        int y1 = query[1] - 1;
        int x2 = query[2] - 1;
        int y2 = query[3] - 1;

        int temp = map[x1][y1];
        int min = temp;

        // 왼쪽 변: 아래 값을 위로
        for (int r = x1; r < x2; r++) {
            map[r][y1] = map[r + 1][y1];
            min = Math.min(min, map[r][y1]);
        }

        // 아래쪽 변: 오른쪽 값을 왼쪽으로
        for (int c = y1; c < y2; c++) {
            map[x2][c] = map[x2][c + 1];
            min = Math.min(min, map[x2][c]);
        }

        // 오른쪽 변: 위 값을 아래로
        for (int r = x2; r > x1; r--) {
            map[r][y2] = map[r - 1][y2];
            min = Math.min(min, map[r][y2]);
        }

        // 위쪽 변: 왼쪽 값을 오른쪽으로
        for (int c = y2; c > y1 + 1; c--) {
            map[x1][c] = map[x1][c - 1];
            min = Math.min(min, map[x1][c]);
        }

        map[x1][y1 + 1] = temp;

        return min;
    }
}