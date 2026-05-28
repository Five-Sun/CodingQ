import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int rowSize = data.length;
        int colSize = data[0].length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            list.add(data[i]);
        }

        sort(list, col - 1);

        int answer = cal(list, row_begin - 1, row_end - 1);
        return answer;
    }

    private void sort(List<int[]> list, int standard) {
        list.sort((o1, o2) -> {
            if (o1[standard] != o2[standard]) return o1[standard] - o2[standard];
            return o2[0] - o1[0];
        });
    }

    private int cal(List<int[]> list, int begin, int end) {
        int result = 0;
        for (int i = begin; i <= end; i++) {
            int sum = 0;
            int[] arr = list.get(i);
            for (int j = 0; j < arr.length; j++) {
                sum += arr[j] % (i + 1);
            }
            result ^= sum;
        }
        return result;
    }
}