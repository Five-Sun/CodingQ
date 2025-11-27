import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        long max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        long left = 0;
        long right = max * M;

        while (left < right) {
            long mid = (left + right) / 2;

            if (isPossible(arr, M, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    static boolean isPossible(int[] arr, int M, long limit) {
        long count = 0;
        for (int i = 0; i < arr.length; i++) {
            count += limit / arr[i];
            if(count >= M) return true;
        }

        return false;
    }
}