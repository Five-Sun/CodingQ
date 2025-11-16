import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        //움직일 포인터
        int left = 0;
        int right = N - 1;
        int answer = 0;
        while (left < right) {
            long sum = arr[left] + arr[right];
            //같으면 -> 사용, 작으면 -> left ++, 크면 -> right--
            if (sum == M) {
                answer++;
                left++;
                right--;
            } else if (sum > M) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(answer);
    }
}