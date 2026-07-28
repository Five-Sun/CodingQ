class Solution {
    public int solution(int[][] signals) {
        long limit = 1;
        for (int[] signal : signals) {
            int cycle = signal[0] + signal[1] + signal[2];
            limit = lcm(limit, cycle);
        }

        for (long time = 1; time <= limit; time++) {
            boolean allYellow = true;

            for (int[] signal : signals) {
                int green = signal[0];
                int yellow = signal[1];
                int cycle = signal[0] + signal[1] + signal[2];

                long position = (time - 1) % cycle;

                if (!(green <= position && green + yellow > position)) {
                    allYellow = false;
                    break;
                }
            }
            if (allYellow) {
                return (int) time;
            }
        }
        return -1;
    }

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
/**
 * n개의 신호등
 * 초, 노, 빨 순 지속시간은 전부 다름
 * 1초부터 시작하ㅗㄱ 시작은 초록색임
 * 모두 노란불이 되면 정전이 발생
 * 그러면 이걸 그냥 다 시간 기준 시뮬레이션을 돌릴것인가?
 *
 */