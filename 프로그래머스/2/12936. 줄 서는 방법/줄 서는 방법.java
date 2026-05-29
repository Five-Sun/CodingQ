import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];

        List<Integer> numbers = new ArrayList<>();
        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
            numbers.add(i);
        }

        k--;

        for (int i = 0; i < n; i++) {
            long blockSize = fact[n - 1 - i];
            int index = (int) (k / blockSize);

            answer[i] = numbers.remove(index);

            k = k % blockSize;
        }

        return answer;
    }

}