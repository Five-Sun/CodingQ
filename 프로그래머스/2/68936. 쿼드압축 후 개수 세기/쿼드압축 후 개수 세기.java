class Solution {
    static int[] answer;
    public int[] solution(int[][] arr) {
        answer = new int[2];
        divide(arr, 0, 0, arr.length);
        return answer;
    }

    static void divide(int[][] arr, int startR, int startC, int size) {
        int standard = arr[startR][startC];
        boolean flag = true;
        for (int r = startR; r < startR + size; r++) {
            for (int c = startC; c < startC + size; c++) {
                if (arr[r][c] != standard) {
                    flag = false;
                    break;
                }
            }
            if (!flag) break;
        }
        
        if (flag) {
            answer[standard]++;
        } else {
            divide(arr, startR, startC, size / 2);
            divide(arr, startR + size / 2, startC, size / 2);
            divide(arr, startR, startC + size / 2, size / 2);
            divide(arr, startR+ size / 2, startC + size / 2, size / 2);
        }
    }
}