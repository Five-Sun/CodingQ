import java.util.Arrays;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        for (int i = 0; i < schedules.length; i++) {
            int deadline = toMins(schedules[i]) + 10;
            boolean success = true;

            for (int day = 0; day < 7; day++) {
                int currentDay = (startday - 1 + day) % 7 + 1;

                if (currentDay >= 6) {
                    continue;
                }

                if (toMins(timelogs[i][day]) > deadline) {
                    success = false;
                    break;
                }
            }

            if (success) {
                answer++;
            }
        }
        
        return answer;
    }

    public static int toMins (int time) {
        int hour = time / 100;
        int minute = time % 100;
        
        return hour * 60 + minute;
    }
}