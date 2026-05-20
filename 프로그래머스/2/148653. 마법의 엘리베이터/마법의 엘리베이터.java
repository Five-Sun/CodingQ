class Solution {
    static int answer;
    public int solution(int storey) {
        answer = Integer.MAX_VALUE;
        cal(storey, 0);
        return answer;
    }

    static void cal(int num, int count) {
        if (num == 0) {
            answer = Math.min(answer, count);
            return;
        }
        if ((num / 10) > 0) {
            //올리는 경우
            cal((num + 10) / 10, count + (10 - (num % 10)));

            //내리는 경우
            cal(num / 10 , count + (num % 10));
        } else {
            //올리는 경우
            cal(num / 10, count + (10 - (num % 10)) + 1);

            //내리는 경우
            cal(num / 10 , count + (num % 10));
        }
    }

    public static void main(String[] args) {
        int storey = 16;
        Solution solution = new Solution();
        System.out.println(solution.solution(storey));
    }
}
/**
 * 완탐문제 같은데, 올림과 내림을 보면서 진행하는 문제?
 * 0이 되면 /10으로 자릿수를 줄이고 이어서 탐색? 근데 모든 경우는 다 봐야 함? 그리디로 가능한가?
 * 재귀로?
 * 올림과 내림 판단은 어려우니까 다 해야하는데, 내림을 몇개 해야하는지 아는 법은 /1로 나누기,
 * 올림은 %10으로
 */