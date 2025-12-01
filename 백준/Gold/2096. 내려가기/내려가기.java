import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] score = new int[3];

        // 최대 점수를 위한 배열
        int[] maxPrev = new int[3];
        int[] maxCurrent = new int[3];

        // 최소 점수를 위한 배열
        int[] minPrev = new int[3];
        int[] minCurrent = new int[3];

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            score[0] = Integer.parseInt(st.nextToken());
            score[1] = Integer.parseInt(st.nextToken());
            score[2] = Integer.parseInt(st.nextToken());

            if (i == 0) {
                maxPrev[0] = minPrev[0] = score[0];
                maxPrev[1] = minPrev[1] = score[1];
                maxPrev[2] = minPrev[2] = score[2];

                continue;
            }

            maxCurrent[0] = score[0] + Math.max(maxPrev[0], maxPrev[1]);
            maxCurrent[1] = score[1] + Math.max(Math.max(maxPrev[0], maxPrev[1]), maxPrev[2]);
            maxCurrent[2] = score[2] + Math.max(maxPrev[1], maxPrev[2]);

            minCurrent[0] = score[0] + Math.min(minPrev[0], minPrev[1]);
            minCurrent[1] = score[1] + Math.min(Math.min(minPrev[0], minPrev[1]), minPrev[2]);
            minCurrent[2] = score[2] + Math.min(minPrev[1], minPrev[2]);

            maxPrev[0] = maxCurrent[0];
            maxPrev[1] = maxCurrent[1];
            maxPrev[2] = maxCurrent[2];

            minPrev[0] = minCurrent[0];
            minPrev[1] = minCurrent[1];
            minPrev[2] = minCurrent[2];
        }

        System.out.println(Math.max(Math.max(maxPrev[0], maxPrev[1]), maxPrev[2]) + " " + Math.min(Math.min(minPrev[0], minPrev[1]), minPrev[2]));
    }
}