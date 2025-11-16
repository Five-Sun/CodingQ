import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long[] arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        //정렬
        Arrays.sort(arr);

        //움직일 포인터
        int left = 0;
        int right = 0;
        long answer = 2_000_000_000;

        //차이를 알려면 큰 수에서 작은 수를 빼라. 그리고 절대값 확인
        //M보다 크게 차이가 나는 경우, Math.min()하기. M인 경우는 종료 시키기
        //인덱스 이동은?
        //차이가 M보다 작으면 right++;
        //차이가 M보다 크면 left++;
        while (left <= right && left < N && right < N) {
            long sum = arr[right] - arr[left];
            long abs = Math.abs(sum);

            if (abs >= M) {
                answer = Math.min(answer, abs);
                left++;
            } else {
                right++;
            }
        }
        System.out.println(answer);
    }
}