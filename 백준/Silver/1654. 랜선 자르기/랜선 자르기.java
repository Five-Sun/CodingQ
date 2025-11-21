import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //랜선의 개수
        int M = Integer.parseInt(st.nextToken()); //필요한 랜선의 개수

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        long left = 1;
        long right = arr[N - 1];
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (isPossible(arr, mid, M)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    static boolean isPossible(long[] arr, long mid, int M) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= mid) {
                sum += arr[i]/mid;
            }
        }
        return sum >= M;
    }
}