import java.util.*;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long r = d;
        long rSquared = r * r;

        for (long i = 0; i <= d; i += k) {
            long maxY = (long) Math.sqrt(rSquared - i * i);

            answer += (maxY / k) + 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int k = 1;
        int d = 5;

        Solution sol = new Solution();
        System.out.println(sol.solution(k, d));
    }
}