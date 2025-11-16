import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int X = Integer.parseInt(br.readLine());

        int left = 0;
        int right = N - 1;
        int answer = 0;
        //sum이 크면 right--, sum이 작으면 ++, sum이 같으면 뭘 움직여야 하는가?
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (X == sum) {
                answer++;
                left++;
                right--;
            } else if (X < sum) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(answer);
    }
}