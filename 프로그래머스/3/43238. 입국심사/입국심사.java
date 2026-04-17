import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        int maxTime = 0;
        for (int i = 0; i < times.length; i++) {
            maxTime = Math.max(maxTime, times[i]);
        }

        long left = 1;
        long right = (long) maxTime * n;

        while(left <= right) {
            long mid = (left + right) / 2;

            if(isPossible(n, mid, times)) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }

    static boolean isPossible(int n, long m, int[] times) {
        long count = 0;

        for (int i = 0; i < times.length; i++) {
            count += m / times[i];
            if (count >= n) return true;
        }
        
        return false;
    }

/*    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        Solution sol = new Solution();
        System.out.println(sol.solution(n, times));
    }*/
}