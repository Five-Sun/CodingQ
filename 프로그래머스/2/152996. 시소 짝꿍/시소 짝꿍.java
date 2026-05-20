import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Arrays.sort(weights);

        int len = weights.length;
        int count = 0;
        int before = 0;
        for (int i = 0; i < weights.length - 1; i++) {
            if (before != weights[i]) {
                before = weights[i];
                count = 0;
                for (int j = i + 1; j < weights.length; j++) {
                    double div = (double) weights[i] / weights[j];
                    if (div == (double) 1 /2 || div == (double) 2 /3 || div == (double) 3 /4 || div == 1.0) count++;
                }
                answer += count;
            } else {
                count -= 1;
                answer += count;
            }

        }

        return answer;
    }
    
}
/**
 * 정렬 -> while()
 */