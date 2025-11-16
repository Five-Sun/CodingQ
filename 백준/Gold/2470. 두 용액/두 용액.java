import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        //움직일 포인터
        int left = 0;
        int right = N - 1;
        //정답을 저장할 포인터
        int leftIdx = 0;
        int rightIdx = N - 1;

        long answer = 2_000_000_000;
        //sum이 기존 정답 보다 작아? 정답 갱신하기
        //인덱스를 어케 움직여야 할까? 음수냐, 양수냐?
        while (left < right) {
            long sum = arr[left] + arr[right];

            if (Math.abs(sum) < answer) {
                answer = Math.abs(sum);
                leftIdx = left;
                rightIdx = right;
            }
            //양수면 right--
            if (sum >= 0) {
                if (sum == 0) {
                    break;
                }
                right--;
            }
            //음수면 left++
            else {
                left++;
            }
        }
        System.out.println(arr[leftIdx] + " " + arr[rightIdx]);
    }
}