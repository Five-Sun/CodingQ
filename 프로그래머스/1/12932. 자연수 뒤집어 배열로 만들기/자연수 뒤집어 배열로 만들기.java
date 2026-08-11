import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(long n) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add((int) (n % 10));
            n /= 10;
        }

        answer = list.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}