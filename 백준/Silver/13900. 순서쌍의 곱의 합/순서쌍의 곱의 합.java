import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long sum = 0;
        long sumSq = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            sumSq += (long) Math.pow(arr[i], 2);
        }
        System.out.println((sum * sum - sumSq) / 2);
    }
}