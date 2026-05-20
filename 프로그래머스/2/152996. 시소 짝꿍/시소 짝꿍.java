import java.util.Arrays;
class Solution {
    public long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);

        for(int i = 0; i < weights.length-1; i++){
            for(int j = i + 1; j < weights.length; j++){
                if(weights[i]*2 < weights[j]) break;
                if(weights[i] == weights[j]) {
                    answer++;
                    continue;
                }
                if(weights[i]*3 == weights[j]*2) {
                    answer++;
                    continue;
                }
                if(weights[i]*4 == weights[j]*2) {
                    answer++;
                    continue;
                }
                if(weights[i]*4 == weights[j]*3) {
                    answer++;
                    continue;
                }
            }
        }

        return answer;
    }
}
