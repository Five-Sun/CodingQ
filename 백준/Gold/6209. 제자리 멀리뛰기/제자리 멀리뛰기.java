import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] rocks = new int[N];

        for (int i = 0; i < N; i++) {
            int rock = Integer.parseInt(br.readLine());
            rocks[i] = rock;
        }

        Arrays.sort(rocks);

        int left = 0;
        int right = D;

        int answer = 0;
        while(left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(rocks, mid, M)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    public static boolean isPossible(int[] rocks, int dist, int M) {
        int removed = 0;
        int prev = 0;

        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i] - prev < dist) {
                removed++;
            } else {
                prev = rocks[i];
            }
        }

        return removed <= M;
    }
}