import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        int round = 0;
        int rest = N;
        while (q.size() > 1) { //한명이 남을 때까지 게임 반봉
            round++; //반복마다 round 증가
            int count = 0;
            int recur = ((((round * round) % rest) * round) % rest) + rest; //오버플로우 방지, 실제로 반복하면 되는 횟수

            while (true) {
                count++;
                if (count == recur) {
                    //탈락이십니다.
                    q.poll();
                    rest--;
                    break;
                } else {
                    q.offer(q.poll());
                }
            }
        }
        System.out.println(q.poll()); //남은 한명이 당첨
    }
}