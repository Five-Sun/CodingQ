import java.util.*;

class Solution {
    public int[] solution(long begin, long end) {
        int len = (int) (end - begin) + 1;
        int[] answer = new int[len];

        for (long i = begin; i <= end; i++) {
            int idx = (int) (i - begin);

            if (i == 1) {
                answer[idx] = 0;
                continue;
            }

            long maxDivisor = 1;

            for (long j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    long biggest = i / j;

                    if (biggest <= 10000000) {
                        maxDivisor = biggest;
                        break;
                    }
                    
                    maxDivisor = j;
                }
            }
            
            answer[idx] = (int) maxDivisor;
        }
        return answer;
    }
}