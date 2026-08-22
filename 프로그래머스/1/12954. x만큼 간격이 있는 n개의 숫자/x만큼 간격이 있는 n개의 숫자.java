import java.util.ArrayList;
import java.util.List;

class Solution {
    public long[] solution(int x, int n) {
        long[] answer = {};
        List<Long> list = new ArrayList<>();
        long temp = 0;
        while (n-- > 0) {
            list.add(temp += x);
        }
        answer = list.stream().mapToLong(l -> l).toArray();
        return answer;
    }
}