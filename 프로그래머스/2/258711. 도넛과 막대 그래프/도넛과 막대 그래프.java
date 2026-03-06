import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int maxNum = 0;
        for (int[] e : edges) {
            maxNum = Math.max(maxNum, Math.max(e[0], e[1]));
        }

        int[] in = new int[maxNum + 1];
        int[] out = new int[maxNum + 1];
        boolean[] present = new boolean[maxNum + 1];

        for (int[] e : edges) {
            int a = e[0], b = e[1];
            present[a] = true;
            present[b] = true;
            out[a]++;
            in[b]++;
        }

        int gen = 0, stick = 0, eight = 0;

        for (int i = 1; i <= maxNum; i++) {
            if (!present[i]) continue;

            if (in[i] == 0 && out[i] >= 2) gen = i;
            else if (out[i] == 0) stick++;
            else if (out[i] == 2 && in[i] >= 2) eight++;
        }

        int donut = out[gen] - stick - eight;
        return new int[]{gen, donut, stick, eight};
    }
}