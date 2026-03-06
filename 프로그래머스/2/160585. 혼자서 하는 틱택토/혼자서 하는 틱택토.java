class Solution {
    public int solution(String[] board) {
        char[][] map = new char[3][3];
        int o = 0, x = 0;

        for (int r = 0; r < 3; r++) {
            String row = board[r];
            for (int c = 0; c < 3; c++) {
                char ch = row.charAt(c);
                map[r][c] = ch;
                if (ch == 'O') o++;
                else if (ch == 'X') x++;
            }
        }

        // 개수 기본 조건
        if (x > o) return 0;
        if (o > x + 1) return 0;

        boolean winO = isWin(map, 'O');
        boolean winX = isWin(map, 'X');

        // 둘 다 이길 수 없음
        if (winO && winX) return 0;

        // O가 이겼으면 O가 1개 더 많아야 함
        if (winO && o != x + 1) return 0;

        // X가 이겼으면 O와 X 개수가 같아야 함
        if (winX && o != x) return 0;

        return 1;
    }

    private boolean isWin(char[][] m, char p) {
        // rows
        for (int r = 0; r < 3; r++) {
            if (m[r][0] == p && m[r][1] == p && m[r][2] == p) return true;
        }
        // cols
        for (int c = 0; c < 3; c++) {
            if (m[0][c] == p && m[1][c] == p && m[2][c] == p) return true;
        }
        // diagonals
        if (m[0][0] == p && m[1][1] == p && m[2][2] == p) return true;
        if (m[0][2] == p && m[1][1] == p && m[2][0] == p) return true;

        return false;
    }
}