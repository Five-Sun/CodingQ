import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[][] q = {{1, 2, 3, 4, 5}
                , {2, 1, 2, 3, 2, 4, 2, 5}
                , {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        int[] hits = new int[3];
        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < q.length; j++) {
                if (answers[i] == q[j][i % q[j].length]) {
                    hits[j]++;
                }
            }
        }
        int maxHit = Math.max(hits[0], Math.max(hits[1], hits[2]));
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < hits.length; i++) {
            if (hits[i] == maxHit) {
                list.add(i + 1);
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}