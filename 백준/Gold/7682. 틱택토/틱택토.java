import java.io.*;
import java.util.*;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    static int N, M;
    static List<Node>[] graph;

    public static class Node {
        int v, w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        while(true) {
            String s = br.readLine();
            if (s.equals("end")) break;

            char[] board = s.toCharArray();

            int xCnt = 0;
            int oCnt = 0;
            int dotCnt = 0;

            for (char ch : board) {
                if (ch == 'X') xCnt++;
                else if (ch == 'O') oCnt++;
                else dotCnt++;
            }

            boolean xWin = isWin(board, 'X');
            boolean oWin = isWin(board, 'O');

            boolean valid = false;

            if (xCnt == oCnt || xCnt == oCnt + 1) {
                if (xWin && oWin) {
                    valid = false;
                } else if (xWin) {
                    if (xCnt == oCnt + 1) valid = true;
                } else if (oWin) {
                    if (xCnt == oCnt) valid = true;
                } else {
                    if (dotCnt == 0 && xCnt == oCnt + 1) valid = true;
                }
            }

            sb.append(valid ? "valid" : "invalid").append("\n");
        }

        System.out.println(sb);
    }

    static boolean isWin(char[] b, char c) {
        return
            // 가로
            (b[0] == c && b[1] == c && b[2] == c) ||
            (b[3] == c && b[4] == c && b[5] == c) ||
            (b[6] == c && b[7] == c && b[8] == c) ||

            // 세로
            (b[0] == c && b[3] == c && b[6] == c) ||
            (b[1] == c && b[4] == c && b[7] == c) ||
            (b[2] == c && b[5] == c && b[8] == c) ||

            // 대각선
            (b[0] == c && b[4] == c && b[8] == c) ||
            (b[2] == c && b[4] == c && b[6] == c);
    }

}
/**
 * 조건 검사 순서
 * X가 승리하는 조건이 있는지 확인 -> X가 한개만 딱 많아야 함
 * O가 승리하는 조건이 있는지 확인 -> X와 O의 갯수가 동일 해야 함
 * 아무도 승리하지 못하는 조건에선 빈칸을 제외한 채워진 칸에 따라 달라짐
 * 홀수면 X가 하나더 많, 짝수면 갯수 가 같은지
 * 1. 가로 체크
 * 2. 세로 체크
 * 3. 대각선 체크
 */