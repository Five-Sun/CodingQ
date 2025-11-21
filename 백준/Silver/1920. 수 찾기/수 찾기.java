import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            int num = Integer.parseInt(st.nextToken());

            int left = 0;
            int right = N -1;
            boolean flag = false;
            while (left <= right) {
                int mid = (left + right) / 2;

                if (arr[mid] == num) {
                    sb.append(1).append("\n");
                    flag = true;
                    break;
                } else if (arr[mid] > num) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (!flag) {
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb);
    }
}