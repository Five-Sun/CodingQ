import java.io.*;
import java.util.*;

class Main {
    static char[][] map;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //초기화
        map = new char[N][N];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < N; c++) {
                map[r][c] = str.charAt(c);
            }
        }

        int answer = 0;
        //하나씩 순회하면서 옆에 거랑 다르면 바꿔보기
        for (int i = 0; i < N; i++) {
            //1. 바꾸기 전 행 최대 길이 찾기
            answer = Math.max(answer, checkRow(i));
            //2. 바꾸기 전 열 최대 길이 찾기
            answer = Math.max(answer, checkCol(i));
            for (int j = 0; j < N - 1; j++) {
                //3. 행 바꾸기 -> 각 2개의 행을 확인
                if (map[j][i] != map[j + 1][i]) {
                    swapRow(j, i);
                    answer = Math.max(answer, checkCol(i));
                    answer = Math.max(answer, checkRow(j));
                    answer = Math.max(answer, checkRow(j + 1));
                    swapRow(j, i);
                }
                //4. 열 바꾸기 -> 각 두개의 열을 확인
                if (map[i][j] != map[i][j + 1]) {
                    swapCol(i, j);
                    answer = Math.max(answer, checkRow(i));
                    answer = Math.max(answer, checkCol(j));
                    answer = Math.max(answer, checkCol(j + 1));
                    swapCol(i, j);
                }
            }
        }
        System.out.println(answer);
    }
    //1. 행 기준 가장 긴 길이 찾기
    static int checkRow(int row) {
        int result = 0;
        int count = 1;
        char standard = map[row][0];
        for (int c = 1; c < N; c++) {
            if (standard == map[row][c]) {
                count++;
            } else {
                count = 1;
                standard = map[row][c];
            }
            result = Math.max(result, count);
        }
        return result;
    }
    //2. 열 기준 가장 긴 길이 찾기
    static int checkCol(int col) {
        int result = 0;
        int count = 1;
        char standard = map[0][col];
        for (int r = 1; r < N; r++) {
            if (standard == map[r][col]) {
                count++;
            } else {
                count = 1;
                standard = map[r][col];
            }
            result = Math.max(result, count);
        }
        return result;
    }
    static void swapRow(int row, int col) {
        char temp = map[row][col];
        map[row][col] = map[row + 1][col];
        map[row + 1][col] = temp;
    }
    //4. 열 바꾸기
    static void swapCol(int row, int col){
        char temp = map[row][col];
        map[row][col] = map[row][col + 1];
        map[row][col + 1] = temp;
    }
}
//N * N 사탕
//사탕의 색이 다른 인접한 두 칸
//swap
//가장 긴 연손 부분을 고른 후, 먹는다
//빨 C / 파 P / 초 Z / 노 Y
//최대는 N인데, 행을 바꾸면 바뀐 두개의 열과 해당 행 확인, 열을 바꾸면 바뀐 두개의 행과 해당 열 확인