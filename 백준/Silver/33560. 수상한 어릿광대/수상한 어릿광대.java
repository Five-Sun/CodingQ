import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        Queue<Integer> q = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            q.offer(Integer.parseInt(st.nextToken()));
        }

        int[] result = new int[4];

        int time = 0;
        int term = 4;
        int score = 0;
        int point = 1;

        while (!q.isEmpty()) {

            // 이전 턴 처리 결과로 240초를 초과했다면,
            // 현재 게임 종료 후 새 게임 시작
            if (time > 240) {
                addReward(score, result);
                time = 0;
                term = 4;
                score = 0;
                point = 1;
            }

            int cur = q.poll();

            switch (cur) {
                case 1:
                    // 즉시 현재 게임 종료
                    addReward(score, result);
                    time = 0;
                    term = 4;
                    score = 0;
                    point = 1;
                    break;

                case 2:
                    if (point > 1) point /= 2;
                    else term += 2;

                    score += point;
                    time += term;
                    break;

                case 3:
                    score += point;
                    time += term;
                    break;

                case 4:
                    time += 56;
                    score += point;
                    time += term;
                    break;

                case 5:
                    if (term > 1) term--;

                    score += point;
                    time += term;
                    break;

                case 6:
                    if (point < 32) point *= 2;

                    score += point;
                    time += term;
                    break;
            }
        }

        for (int res : result) {
            System.out.println(res);
        }
    }

    static void addReward(int score, int[] result) {
        if (score >= 35 && score < 65) {
            result[0]++;
        } else if (score >= 65 && score < 95) {
            result[1]++;
        } else if (score >= 95 && score < 125) {
            result[2]++;
        } else if (score >= 125) {
            result[3]++;
        }
    }
}